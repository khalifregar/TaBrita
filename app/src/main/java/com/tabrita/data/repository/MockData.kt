package com.tabrita.data.repository

import com.tabrita.domain.model.Article
import com.tabrita.domain.model.Category
import com.tabrita.domain.model.ContentBlock
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * Curated high-quality mock data for TaBrita demo.
 * All content in Bahasa Indonesia for authentic experience.
 */
object MockData {

    private val now = Instant.now()

        val articles = listOf(
        Article(
            id = "art001",
            title = "Pemerintah Luncurkan Program Transformasi Digital Nasional 2026",
            description = "Langkah besar untuk mempercepat adopsi teknologi di seluruh sektor pemerintahan dan UMKM Indonesia.",
            thumbnailUrl = "https://picsum.photos/id/1015/1200/800",
            source = "Kompas",
            author = "Rina Marlina",
            publishedAt = now.minus(2, ChronoUnit.HOURS),
            category = Category.POLITIK,
            readTimeMinutes = 6,
            url = "https://example.com/art001",
            contentBlocks = listOf(ContentBlock("Langkah besar untuk mempercepat adopsi teknologi di seluruh sektor pemerintahan dan UMKM Indonesia."))
        ),
        Article(
            id = "art002",
            title = "Startup AI Lokal Raih Pendanaan Seri B Senilai Rp 450 Miliar",
            description = "Nusantara Intelligence menjadi startup AI pertama di Asia Tenggara yang mencapai valuasi unicorn.",
            thumbnailUrl = "https://picsum.photos/id/1018/1200/800",
            source = "Tech in Asia",
            author = "Dian Sastro",
            publishedAt = now.minus(5, ChronoUnit.HOURS),
            category = Category.TEKNOLOGI,
            readTimeMinutes = 5,
            url = "https://example.com/art002",
            contentBlocks = listOf(ContentBlock("Nusantara Intelligence menjadi startup AI pertama di Asia Tenggara yang mencapai valuasi unicorn."))
        ),
        Article(
            id = "art003",
            title = "Indeks Harga Saham Gabungan Tembus 8.200, Rekor Baru Pasar Modal",
            description = "Investor asing kembali masuk deras seiring fundamental ekonomi yang solid dan suku bunga global yang stabil.",
            thumbnailUrl = "https://picsum.photos/id/1033/1200/800",
            source = "Bisnis Indonesia",
            author = "Farhan Maulana",
            publishedAt = now.minus(8, ChronoUnit.HOURS),
            category = Category.BISNIS,
            readTimeMinutes = 4,
            url = "https://example.com/art003",
            contentBlocks = listOf(ContentBlock("Investor asing kembali masuk deras seiring fundamental ekonomi yang solid dan suku bunga global yang stabil."))
        )
    )
}




