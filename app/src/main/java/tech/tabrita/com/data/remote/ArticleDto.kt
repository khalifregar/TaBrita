package tech.tabrita.com.data.remote

import com.google.gson.annotations.SerializedName

/**
 * DTOs for the local Python TaBrita Scraper API response.
 * Matches the structure produced by scripts/convert_to_tabrita.py
 * (and tabrita_seed.json). We map to domain models in the repository.
 */
data class ArticlesResponse(
    @SerializedName("articles") val articles: List<ArticleDto> = emptyList(),
    @SerializedName("total") val total: Int = 0,
    @SerializedName("page") val page: Int = 1,
    @SerializedName("limit") val limit: Int = 20,
    @SerializedName("has_more") val hasMore: Boolean = false
)

data class ArticleDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String,
    @SerializedName("source") val source: String,
    @SerializedName("author") val author: String,
    @SerializedName("publishedAt") val publishedAt: String, // ISO string, e.g. "2026-06-03T03:52:23.980485Z"
    @SerializedName("category") val category: String,       // "POLITIK", "BISNIS" etc. (matches enum name)
    @SerializedName("readTimeMinutes") val readTimeMinutes: Int,
    @SerializedName("url") val url: String = "",
    @SerializedName("contentBlocks") val contentBlocks: List<ContentBlockDto> = emptyList()
)

data class ContentBlockDto(
    @SerializedName("text") val text: String = "",
    @SerializedName("imageUrl") val imageUrl: String? = null
)
