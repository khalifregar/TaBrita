package tech.tabrita.com.data.repository

import tech.tabrita.com.data.local.BookmarkDao
import tech.tabrita.com.data.local.BookmarkEntity
import tech.tabrita.com.data.remote.ArticleMapper
import tech.tabrita.com.data.remote.TaBritaApiService
import tech.tabrita.com.domain.model.Article
import tech.tabrita.com.domain.model.Category
import tech.tabrita.com.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ArticleRepository that prefers real data fetched from the local Python scraper API
 * (news-scraper/scripts/api_server.py + tabrita_seed.json with full rich contentBlocks).
 *
 * Falls back to MockData (curated rich examples) if the API is not running or fails.
 * This way Kotlin can "nangkap" (catch) the real scraped data dynamically.
 */
@Singleton
class ArticleRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao,
    private val apiService: TaBritaApiService
) : ArticleRepository {

    // In-memory cache for the session (loaded from remote on first use)
    private var remoteArticles: List<Article>? = null

    private suspend fun getRemoteOrFallback(): List<Article> {
        if (remoteArticles != null) return remoteArticles!!

        return try {
            // Fetch a good chunk from the local API (scraper must be running)
            val response = apiService.getArticles(limit = 200, page = 1)
            val mapped = response.articles.map { ArticleMapper.toDomain(it) }
            remoteArticles = mapped
            mapped
        } catch (e: Exception) {
            // API not running / network error -> use the rich MockData as fallback
            // (still has real enriched examples from previous scraper runs)
            MockData.articles.also { remoteArticles = it }
        }
    }

    override suspend fun getAllArticles(): List<Article> {
        return getRemoteOrFallback()
    }

    override suspend fun getArticleById(id: String): Article? {
        val list = getRemoteOrFallback()
        // Try remote get by id for precision if possible
        return try {
            val dto = apiService.getArticleById(id)
            ArticleMapper.toDomain(dto)
        } catch (e: Exception) {
            list.find { it.id == id }
        }
    }

    override suspend fun getArticlesByCategory(category: Category): List<Article> {
        val list = getRemoteOrFallback()
        return if (category == Category.ALL) {
            list
        } else {
            list.filter { it.category == category }
        }
    }

    override suspend fun searchArticles(query: String): List<Article> {
        val list = getRemoteOrFallback()
        if (query.isBlank()) return list

        val lowerQuery = query.lowercase()
        return list.filter { article ->
            article.title.lowercase().contains(lowerQuery) ||
                article.description.lowercase().contains(lowerQuery) ||
                article.contentBlocks.any { it.text.lowercase().contains(lowerQuery) } ||
                article.source.lowercase().contains(lowerQuery) ||
                article.author.lowercase().contains(lowerQuery)
        }
    }

    override suspend fun getTrendingArticles(limit: Int): List<Article> {
        val list = getRemoteOrFallback()
        // Simple "trending" = shuffle for demo (in real could sort by published or score)
        return list.shuffled().take(limit)
    }

    override suspend fun getFeaturedArticles(): List<Article> {
        val list = getRemoteOrFallback()
        return list.filter { it.category != Category.ALL }
            .distinctBy { it.category }
            .take(3)
    }

    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return bookmarkDao.getAllBookmarks().map { bookmarks ->
            val bookmarkedIds = bookmarks.map { it.articleId }.toSet()
            // Use the already-loaded (or fallback) list for mapping bookmarks.
            // The list is populated on first get* call.
            val listToUse = remoteArticles ?: MockData.articles
            listToUse.filter { it.id in bookmarkedIds }
                .sortedByDescending { article ->
                    bookmarks.find { it.articleId == article.id }?.bookmarkedAt ?: 0L
                }
        }
    }

    override suspend fun toggleBookmark(articleId: String): Boolean {
        val isCurrentlyBookmarked = bookmarkDao.isBookmarked(articleId)
        if (isCurrentlyBookmarked) {
            bookmarkDao.deleteByArticleId(articleId)
        } else {
            bookmarkDao.insert(BookmarkEntity(articleId = articleId))
        }
        return !isCurrentlyBookmarked
    }

    override suspend fun isBookmarked(articleId: String): Boolean {
        return bookmarkDao.isBookmarked(articleId)
    }
}

