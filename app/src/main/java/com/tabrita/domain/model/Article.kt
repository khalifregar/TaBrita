package com.tabrita.domain.model

import java.time.Instant

/**
 * Core domain model for a news article.
 */
data class Article(
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String,
    val source: String,
    val author: String,
    val publishedAt: Instant,
    val category: Category,
    val readTimeMinutes: Int,
    val url: String = ""
) {
    val timeAgo: String
        get() = formatTimeAgo(publishedAt)

    private fun formatTimeAgo(instant: Instant): String {
        val now = Instant.now()
        val diffSeconds = now.epochSecond - instant.epochSecond
        val diffMinutes = diffSeconds / 60
        val diffHours = diffMinutes / 60
        val diffDays = diffHours / 24

        return when {
            diffMinutes < 1 -> "Baru saja"
            diffMinutes < 60 -> "$diffMinutes menit lalu"
            diffHours < 24 -> "$diffHours jam lalu"
            diffDays < 7 -> "$diffDays hari lalu"
            else -> "${diffDays / 7} minggu lalu"
        }
    }
}

enum class Category(val displayName: String, val emoji: String) {
    ALL("Semua", "📰"),
    POLITIK("Politik", "🏛️"),
    TEKNOLOGI("Teknologi", "💻"),
    BISNIS("Bisnis", "📈"),
    OLAHRAGA("Olahraga", "⚽"),
    HIBURAN("Hiburan", "🎬"),
    KESEHATAN("Kesehatan", "❤️"),
    SAINS("Sains", "🔬");

    companion object {
        fun fromDisplayName(name: String): Category =
            entries.firstOrNull { it.displayName.equals(name, ignoreCase = true) } ?: ALL
    }
}
