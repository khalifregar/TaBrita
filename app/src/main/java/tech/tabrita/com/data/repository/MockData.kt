package tech.tabrita.com.data.repository

import tech.tabrita.com.domain.model.Article
import tech.tabrita.com.domain.model.Category
import tech.tabrita.com.domain.model.ContentBlock
import java.time.Instant

/**
 * Curated high-quality mock data for TaBrita demo.
 * Includes real scraped articles (from detik/kompas/tempo enrich) with rich contentBlocks
 * so that clicking shows full data (text slices + images per slice) in ArticleDetailScreen.
 */
object MockData {

    val articles = listOf(
        // Real scraped + enriched (rich slices for clickable display)
        Article(
            id = "4fb6bc483b345ba0",
            title = "Nestapa Warga Kemayoran, Harta Benda Sekejap Jadi Jelaga",
            description = "Kebakaran hebat permukiman padat di Jalan Kemayoran Gempol menyisakan nestapa warga. 250 rumah hangus, 350 KK terdampak.",
            thumbnailUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/kebakaran-kebon-kosong-hanguskan-250-rumah-1780377368746_43.jpeg?w=210&q=90",
            source = "detik.com",
            author = "Redaksi detik.com",
            publishedAt = Instant.parse("2026-06-02T14:23:59.968104Z"),
            category = Category.POLITIK,
            readTimeMinutes = 3,
            url = "https://news.detik.com/berita/d-8515143/nestapa-warga-kemayoran-harta-benda-sekejap-jadi-jelaga",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/kebakaran-kebon-kosong-hanguskan-250-rumah-1780377368801_169.jpeg?w=620"),
                ContentBlock(text = "Kebakaran hebat permukiman padat di Jalan Kemayoran Gempol, Kebon Kosong, Kemayoran. Kebakaran ini kini menyisakan nestapa para warga yang menjadi korban. Sebanyak 250 rumah warga dilaporkan hangus terbakar. 350 keluarga terdampak.", imageUrl = null),
                ContentBlock(text = "Kapolsek Kemayoran Kompol Agung Ardiyansyah menerangkan pihaknya menerima informasi kebakaran terjadi pukul 21.10 WIB Senin (1/6) malam. Kebakaran terjadi di RT 02 RW 04 belakang Pasar Jiung. Kebakaran diduga akibat korsleting listrik.", imageUrl = null)
            )
        ),
        Article(
            id = "f31d9695dd1a1be3",
            title = "Nvidia Pamer RTX Spark, Prosesor Super untuk PC Windows",
            description = "Nvidia perkenalkan prosesor super untuk AI PC di Computex 2026, menyeberang dari pembuat kartu grafis menjadi produsen chip PC konsumen.",
            thumbnailUrl = "https://akcdn.detik.net.id/visual/2026/06/02/nvidia-rtx_169.jpeg?w=250&q=90",
            source = "detik.com",
            author = "Redaksi detik.com",
            publishedAt = Instant.parse("2026-06-02T14:23:59.974330Z"),
            category = Category.TEKNOLOGI,
            readTimeMinutes = 4,
            url = "https://inet.detik.com/consumer/d-8514824/nvidia-pamer-rtx-spark-prosesor-super-untuk-pc-windows",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2024/11/14/ceo-nvidia-jensen-huang-4_169.jpeg"),
                ContentBlock(text = "Nvidia secara resmi menyeberang dari sekadar pembuat kartu grafis menjadi produsen chip PC konsumen. RTX Spark adalah prosesor super untuk AI PC Windows.", imageUrl = null),
                ContentBlock(text = "Nvidia juga mengonfirmasi akan menghadirkan varian yang lebih murah di masa mendatang, dengan konfigurasi lebih terjangkau untuk pasar massal.", imageUrl = null)
            )
        ),
        Article(
            id = "70eaaae2c3839612",
            title = "Hasil Indonesia Open 2026: Sikat Tunggal Taiwan, Putri KW ke 16 Besar",
            description = "Putri Kusuma Wardani melenggang ke 16 besar Indonesia Open 2026 usai mengalahkan tunggal Taiwan.",
            thumbnailUrl = "https://akcdn.detik.net.id/community/media/visual/2026/01/21/putri-kusuma-wardani-melaju-mulus-ke-16-besar-indonesia-masters-2026-1769002329196_43.jpeg?w=210&q=90",
            source = "detik.com",
            author = "Redaksi detik.com",
            publishedAt = Instant.parse("2026-06-02T14:23:59.978286Z"),
            category = Category.OLAHRAGA,
            readTimeMinutes = 3,
            url = "https://sport.detik.com/raket/d-8515104/hasil-indonesia-open-2026-sikat-tunggal-taiwan-putri-kw-ke-16-besar",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/api/wm/2026/01/21/putri-kusuma-wardani-melaju-mulus-ke-16-besar-indonesia-masters-2026-1769002329196_169.jpeg?wid=54&w=1200&v=1&t=jpeg"),
                ContentBlock(text = "Putri Kusuma Wardani melenggang ke 16 besar Indonesia Open 2026. Tunggal Putri andalan tuan rumah itu menang dua gim langsung.", imageUrl = null)
            )
        ),
        Article(
            id = "5ea70588218eb7af",
            title = "Sinopsis Episode 7 Love & 10 Million Dollars: Kecelakaan Maut dan Rencana Busuk Vero",
            description = "Serial Love & 10 Million Dollars memasuki episode ketujuh. Emosi meledak membuat Arman kehilangan fokus hingga menyebabkan kecelakaan.",
            thumbnailUrl = "https://asset.kompas.com/crops/XhXcbdTp3CwbC8atVNZ4MUgXeZM=/0x0:0x0/230x152/data/photo/2026/06/02/6a1ebe5c9779e.jpg",
            source = "kompas.com",
            author = "Kompas.com",
            publishedAt = Instant.parse("2026-06-02T14:24:00.008548Z"),
            category = Category.HIBURAN,
            readTimeMinutes = 4,
            url = "https://www.kompas.com/hype/read/2026/06/02/183731766/sinopsis-episode-7-love-10-million-dollars-kecelakaan-maut-dan-rencana",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://asset.kompas.com/crops/r4zNIeqVN-mbvCL522U21U4gv6o=/0x0:0x0/1200x675/filters:watermark(data/photo/2026/01/30/697c8191ee571.png,0,-0,1)/data/photo/2026/06/02/6a1ebe5c9779e.jpg"),
                ContentBlock(text = "JAKARTA, KOMPAS.com - Serial Love & 10 Million Dollars memasuki episode ketujuhnya. Hubungan Elina dan Arman semakin rumit. Emosi yang meledak membuat Arman kehilangan fokus hingga menyebabkan kecelakaan yang membuat mobil menabrak pembatas jalan.", imageUrl = null)
            )
        ),
        Article(
            id = "c26855d1477059cd",
            title = "Menelusuri Jejak Kereta Tua di Gunung Alishan Taiwan",
            description = "Alishan di Taiwan menawarkan wisata sejarah kereta api tua yang melintasi hutan dan kebun teh.",
            thumbnailUrl = "https://statik.tempo.co/data/2026/04/13/id_1468529/1468529_720.jpg",
            source = "tempo.co",
            author = "Redaksi tempo.co",
            publishedAt = Instant.parse("2026-06-02T14:24:00.031264Z"),
            category = Category.HIBURAN,
            readTimeMinutes = 5,
            url = "https://www.tempo.co/hiburan/menelusuri-jejak-kereta-tua-di-gunung-alishan-taiwan-2128561",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/04/13/id_1468529/1468529_720.jpg"),
                ContentBlock(text = "Suasana taman wisata sekaligus taman konservasi Alishan di Kabupaten Chiayi, Taiwan. Kereta api tua menjadi daya tarik wisatawan yang ingin menikmati pemandangan alam pegunungan.", imageUrl = null)
            )
        ),
        Article(
            id = "f0c26d6e8cf72ded",
            title = "9 Fakta Nomor HP Baru Wajib Rekam Data Wajah Pengguna Berlaku 1 Juli 2026",
            description = "Aturan baru registrasi nomor HP mewajibkan rekam data wajah mulai 1 Juli 2026 untuk validasi biometrik.",
            thumbnailUrl = "https://akcdn.detik.net.id/community/media/visual/2026/05/29/komdigi-1780027028475_43.jpeg",
            source = "detik.com",
            author = "Redaksi detik.com",
            publishedAt = Instant.parse("2026-06-02T14:23:59.974348Z"),
            category = Category.TEKNOLOGI,
            readTimeMinutes = 4,
            url = "https://inet.detik.com/law-and-policy/d-8514489/9-fakta-nomor-hp-baru-wajib-rekam-data-wajah-pengguna-berlaku-1-juli-2026",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2026/05/29/komdigi-1780027028475_43.jpeg"),
                ContentBlock(text = "Dalam satu bulan lagi, aturan pendaftaran untuk nomor HP baru dengan divalidasi pengenalan wajah akan berlaku. Komdigi menegaskan kebijakan SIM card biometrik face recognition.", imageUrl = null)
            )
        ),
        Article(
            id = "f22446a7bf7116d7",
            title = "Kisah Ibu-ibu di Ende Bangkit dari KDRT Berkat Program ATENSI Kemensos",
            description = "Ibu-ibu di Ende, NTT bangkit dari kekerasan dalam rumah tangga berkat bantuan usaha dari Kementerian Sosial.",
            thumbnailUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/ilustrasi-penenun-kain-dari-ende-1780404603861_43.jpeg?w=210&q=90",
            source = "detik.com",
            author = "Redaksi detik.com",
            publishedAt = Instant.parse("2026-06-02T14:23:59.968119Z"),
            category = Category.POLITIK,
            readTimeMinutes = 5,
            url = "https://news.detik.com/berita/d-8515122/kisah-ibu-ibu-di-ende-bangkit-dari-kdrt-berkat-program-atensi-kemensos",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/api/wm/2026/06/02/ilustrasi-penenun-kain-dari-ende-1780404603861_169.jpeg?wid=54&w=1200&v=1&t=jpeg"),
                ContentBlock(text = "Namun, hal paling menyakitkan bukanlah kemiskinan itu sendiri. Luka terdalam justru datang dari orang terdekat. Berkat program ATENSI Kemensos, para ibu mendapatkan bantuan usaha dan harapan baru.", imageUrl = null)
            )
        ),
        Article(
            id = "bb18bfc805e3f1fd",
            title = "Polisi Selidiki Motif Tetangga Siram Air ke Orang Mau Salat di Tangerang",
            description = "Polsek Pasar Kemis mendalami kasus tetangga yang menyiramkan air ke warga yang hendak salat. Korban dan pelaku saling lapor.",
            thumbnailUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/viral-tetangga-sering-siram-air-ke-orang-yang-lewat-mau-salat-di-tangerang-1780372548436_43.jpeg?w=210&q=90",
            source = "detik.com",
            author = "Redaksi detik.com",
            publishedAt = Instant.parse("2026-06-02T14:23:59.968112Z"),
            category = Category.POLITIK,
            readTimeMinutes = 3,
            url = "https://news.detik.com/berita/d-8515133/polisi-selidiki-motif-tetangga-siram-air-ke-orang-mau-salat-di-tangerang",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/api/wm/2026/06/02/viral-tetangga-sering-siram-air-ke-orang-yang-lewat-mau-salat-di-tangerang-1780372548436_169.jpeg?wid=54&w=1200&v=1&t=jpeg"),
                ContentBlock(text = "Polsek Pasar Kemis, Polresta Tangerang, mengatakan sedang mendalami kasus tetangga yang menyiramkan air ke warga yang hendak salat. \"Saudara A mengaku menerima kekerasan hingga membuat laporan ke Polsek Pasar Kemis,\" ujar Humaedi.", imageUrl = null)
            )
        )
    )
}





