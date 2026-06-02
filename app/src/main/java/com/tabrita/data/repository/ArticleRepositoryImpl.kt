package com.tabrita.data.repository

import com.tabrita.data.local.BookmarkDao
import com.tabrita.data.local.BookmarkEntity
import com.tabrita.domain.model.Article
import com.tabrita.domain.model.Category
import com.tabrita.domain.repository.ArticleRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : ArticleRepository {

    private val allArticles = MockData.articles

    override suspend fun getAllArticles(): List<Article> {
        delay(280) // Simulate network latency for realism
        return allArticles
    }

    override suspend fun getArticleById(id: String): Article? {
        delay(120)
        return allArticles.find { it.id == id }
    }

    override suspend fun getArticlesByCategory(category: Category): List<Article> {
        delay(220)
        return if (category == Category.ALL) {
            allArticles
        } else {
            allArticles.filter { it.category == category }
        }
    }

    override suspend fun searchArticles(query: String): List<Article> {
        delay(180)
        if (query.isBlank()) return allArticles

        val lowerQuery = query.lowercase()
        return allArticles.filter { article ->
            article.title.lowercase().contains(lowerQuery) ||
                article.description.lowercase().contains(lowerQuery) ||
                article.contentBlocks.any { it.text.lowercase().contains(lowerQuery) } ||
                article.source.lowercase().contains(lowerQuery) ||
                article.author.lowercase().contains(lowerQuery)
        }
    }

    override suspend fun getTrendingArticles(limit: Int): List<Article> {
        delay(160)
        // Simulate trending by taking recent + high engagement mock (just shuffle + take)
        return allArticles.shuffled().take(limit)
    }

    override suspend fun getFeaturedArticles(): List<Article> {
        delay(140)
        // Pick 3 high quality featured (first 3 from different categories for variety)
        return allArticles.filter { it.category != Category.ALL }
            .distinctBy { it.category }
            .take(3)
    }

    override fun getBookmarkedArticles(): Flow<List<Article>> {
        return bookmarkDao.getAllBookmarks().map { bookmarks ->
            val bookmarkedIds = bookmarks.map { it.articleId }.toSet()
            allArticles.filter { it.id in bookmarkedIds }
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
