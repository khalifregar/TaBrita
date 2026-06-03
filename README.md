# TaBrita

**TaBrita** adalah aplikasi berita modern, estetik, dan eye-catching yang dibangun dengan Kotlin + Jetpack Compose.

## Fitur Utama

- **Desain Modern & Eye-Catching**: Dark theme utama dengan aksen coral yang vibrant, tipografi elegan, kartu berita yang imersif, hero carousel, dan transisi halus.
- **Beranda (Home)**: Featured stories dengan HorizontalPager, kategori filter cepat, dan feed "Untuk Anda".
- **Jelajah (Explore)**: Pencarian real-time + filter kategori yang powerful.
- **Simpan (Bookmarks)**: Simpan artikel favorit secara persisten menggunakan Room.
- **Detail Artikel**: Pengalaman membaca imersif dengan hero image besar, metadata lengkap, body text yang sangat readable, FAB share & bookmark, serta related articles.
- **Profil**: Pengaturan tema, notifikasi (mock), dan info aplikasi.
- **Arsitektur Bersih**: MVVM + Repository + Hilt + Room + Coil.

## Tech Stack

- Kotlin 2.0 + Jetpack Compose + Material 3
- Hilt untuk Dependency Injection
- Room untuk persistensi bookmark
- Coil 3 untuk image loading

## Backend (Ktor Auth API) - Monorepo

Ada juga backend Kotlin Ktor di dalam monorepo sebagai subproject `:backend`.

- Login, Register, OTP via Email (simulasi)
- Pattern: Repository + Service Layer + DTO (Request/Response + Model)
- JWT authentication
- Lihat `backend/README.md` untuk detail endpoint dan cara menjalankan (`./gradlew :backend:run` di port 9090)

Contoh:
- POST /auth/register
- POST /auth/verify-otp
- POST /auth/login (dapatkan JWT)
- GET /auth/me (protected)
- Navigation Compose
- Coroutines + StateFlow
- Gradle Version Catalog + KSP

## Cara Menjalankan

1. Buka proyek di Android Studio (atau gunakan CLI).
2. Sync Gradle.
3. Run `app` pada device/emulator API 26+.
4. Nikmati pengalaman membaca berita ala modern news app.

## Struktur

```
app/
├── data/
│   ├── local/          # Room (Bookmark)
│   └── repository/     # ArticleRepositoryImpl + MockData
├── di/                 # Hilt modules
├── domain/
│   ├── model/          # Article, Category
│   └── repository/     # Interface
└── ui/
    ├── theme/          # TaBritaColors, Typography, Theme
    ├── components/     # ArticleCard, CategoryChip, SearchBar, Shimmer...
    ├── navigation/
    └── screens/        # Home, Explore, Bookmarks, Profile, Detail + ViewModels
```

## Catatan

- Semua data berita adalah **mock data berkualitas tinggi** dalam Bahasa Indonesia (15+ artikel).
- Untuk integrasi API nyata (NewsAPI / GNews / dll), ganti `ArticleRepositoryImpl` dengan implementasi Retrofit.
- Ikon launcher menggunakan adaptive icon vector (disarankan generate PNG mipmap resolusi tinggi via Android Studio Asset Studio untuk production).

## Lisensi

Internal project — dibuat untuk demonstrasi aplikasi berita Kotlin modern.

---

Dibuat dengan ❤️ menggunakan prinsip clean architecture dan modern Android development.
