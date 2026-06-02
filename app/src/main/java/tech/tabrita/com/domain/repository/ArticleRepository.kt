package tech.tabrita.com.domain.repository

import tech.tabrita.com.domain.model.Article
import tech.tabrita.com.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getAllArticles(): List<Article>
    suspend fun getArticleById(id: String): Article?
    suspend fun getArticlesByCategory(category: Category): List<Article>
    suspend fun searchArticles(query: String): List<Article>
    suspend fun getTrendingArticles(limit: Int = 5): List<Article>
    suspend fun getFeaturedArticles(): List<Article>

    // Bookmarks
    fun getBookmarkedArticles(): Flow<List<Article>>
    suspend fun toggleBookmark(articleId: String): Boolean
    suspend fun isBookmarked(articleId: String): Boolean
}

