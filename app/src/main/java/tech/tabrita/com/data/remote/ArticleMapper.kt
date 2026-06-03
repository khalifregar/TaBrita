package tech.tabrita.com.data.remote

import tech.tabrita.com.domain.model.Article
import tech.tabrita.com.domain.model.Category
import tech.tabrita.com.domain.model.ContentBlock
import java.time.Instant

/**
 * Maps API DTOs -> clean domain models.
 * Keeps the domain free of JSON/retrofit concerns.
 */
object ArticleMapper {

    fun toDomain(dto: ArticleDto): Article {
        val cat = try {
            Category.valueOf(dto.category.uppercase())
        } catch (e: Exception) {
            Category.fromDisplayName(dto.category)
        }

        val published = try {
            Instant.parse(dto.publishedAt)
        } catch (e: Exception) {
            Instant.now()
        }

        return Article(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            thumbnailUrl = dto.thumbnailUrl,
            source = dto.source,
            author = dto.author,
            publishedAt = published,
            category = cat,
            readTimeMinutes = dto.readTimeMinutes,
            url = dto.url,
            contentBlocks = dto.contentBlocks.map { toDomain(it) }
        )
    }

    private fun toDomain(blockDto: ContentBlockDto): ContentBlock =
        ContentBlock(
            text = blockDto.text,
            imageUrl = blockDto.imageUrl
        )
}
