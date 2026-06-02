package tech.tabrita.com.data.repository

import tech.tabrita.com.domain.model.Article
import tech.tabrita.com.domain.model.Category
import tech.tabrita.com.domain.model.ContentBlock
import java.time.Instant

/**
 * Curated high-quality mock data for TaBrita demo.
 * Includes real scraped articles (from detik/kompas/tempo enrich + gali-full-250 related expansion)
 * with rich contentBlocks so that clicking shows full data (text slices + images per slice) in ArticleDetailScreen.
 * More real Tempo rich examples added from deep related expansion (hiburan 162+, etc).
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
            id = "tempo-hiburan-jantung-213",
            title = "Keluhan Penyakit Jantung yang Sering Dianggap Biasa",
            description = "DOKTER Spesialis Jantung dan Pembuluh Darah Bethsaida Hospital Gading Serpong menjelaskan keluhan penyakit jantung yang sering dianggap biasa.",
            thumbnailUrl = "https://statik.tempo.co/data/2025/02/04/id_1374609/1374609_720.jpg",
            source = "tempo.co",
            author = "Redaksi tempo.co",
            publishedAt = Instant.parse("2026-06-02T14:24:00.031215Z"),
            category = Category.KESEHATAN,
            readTimeMinutes = 4,
            url = "https://www.tempo.co/gaya-hidup/keluhan-penyakit-jantung-yang-sering-dianggap-biasa-2142479",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/02/04/id_1374609/1374609_720.jpg"),
                ContentBlock(text = "Ilustrasi penyakit jantung/serangan jantung. Shutterstock", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/02/04/id_1374609/1374609_720.jpg"),
                ContentBlock(text = "DOKTER Spesialis Jantung dan Pembuluh Darah Bethsaida Hospital Gading Serpong Putu Parwata menjelaskan beberapa keluhan penyakit jantung yang sering dianggap biasa oleh masyarakat.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/02/04/id_1374719/1374719_720.jpg")
            )
        ),
        Article(
            id = "tempo-digital-2138706",
            title = "Setengah Anak Indonesia Terpapar Konten Seksual Media Sosial",
            description = "KEMENTERIAN Komunikasi dan Digital mengungkap lebih dari separuh anak Indonesia telah terpapar konten bermuatan seksual di media sosial.",
            thumbnailUrl = "https://statik.tempo.co/data/2025/12/02/id_1444913/1444913_720.jpg",
            source = "tempo.co",
            author = "Redaksi tempo.co",
            publishedAt = Instant.parse("2026-06-02T14:24:00.029738Z"),
            category = Category.TEKNOLOGI,
            readTimeMinutes = 5,
            url = "https://www.tempo.co/digital/setengah-anak-indonesia-terpapar-konten-seksual-media-sosial-2138706",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/01/24/id_1372211/1372211_720.jpg"),
                ContentBlock(text = "Ilustrasi anak bermain gadget/media sosial diawasi orang tua. Shutterstock", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/01/24/id_1372211/1372211_720.jpg"),
                ContentBlock(text = "KEMENTERIAN Komunikasi dan Digital (Komdigi) mengungkap lebih dari separuh anak Indonesia telah terpapar konten bermuatan seksual di media sosial sehingga pelindungan anak di ruang digital menjadi semakin mendesak.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2024/02/23/id_1282132/1282132_720.jpg"),
                ContentBlock(text = "Staf Khusus Menteri Komunikasi dan Digital Alfreno Kautsar menyatakan 50,3 persen anak terpapar konten bermuatan seksual melalui media sosial. Dari 80 juta, 48 persen mengalami kekerasan gender berbasis online.", imageUrl = null)
            )
        ),
        // Real from Tempo gali full (related expansion boost, hiburan cat) - 12 blocks / 7 imgs example
        Article(
            id = "hiburan-sav-2151281",
            title = "Savana Propok Taman Nasional Gunung Rinjani Terbakar",
            description = "KAWASAN Savana Provok Balai Taman Nasional Gunung Rinjani (BTNGR) terbakar. Titik api mulai terlihat Selasa siang, 2 Juni 2026. Upaya pemadaman telah dilakukan.",
            thumbnailUrl = "https://statik.tempo.co/data/2025/08/11/id_1419933/1419933_720.jpg",
            source = "tempo.co",
            author = "Redaksi tempo.co",
            publishedAt = Instant.parse("2026-06-02T14:24:00.031264Z"),
            category = Category.HIBURAN,
            readTimeMinutes = 5,
            url = "https://www.tempo.co/hiburan/savana-propok-taman-nasional-gunung-rinjani-terbakar-2151281",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/08/11/id_1419933/1419933_720.jpg"),
                ContentBlock(text = "Padang savana dari pos pendakian Sembalun menuju puncak Gunung Rinjani, Lombok, Nusa Tenggara Barat, 12 Mei 2012. Dok. Tempo/Aris Andrianto", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/08/11/id_1419933/1419933_720.jpg"),
                ContentBlock(text = "KAWASAN Savana Provok Balai Taman Nasional Gunung Rinjani (BTNGR) terbakar. Titik api mulai terlihat Selasa siang, 2 Juni 2026. Upaya pemadaman telah dilakukan, namun hingga Selasa malam api belum bisa dikendalikan. Kepala Sub Bagian Tata Usaha BTNGR Astekita Ardi menyatakan berdasarkan pemantauan di lapangan, titik api pertama kali terlihat sekitar pukul 11.00 WITA, di kawasan Savana Provok 2.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2024/09/12/id_1336349/1336349_720.jpg"),
                ContentBlock(text = "Kelompok Pengelola Savana Propok segera melakukan upaya pemadaman dengan mengerahkan 15 orang anggota yang bergerak menuju lokasi kejadian pada pukul 13.00 WITA. Pemadaman dilakukan secara intensif hingga pukul 18.00 WITA. Namun demikian, upaya pemadaman belum berhasil mengendalikan seluruh titik api karena keterbatasan personel, logistik, dan peralatan pemadaman yang tersedia di lapangan.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2020/06/06/id_943320/943320_720.jpg"),
                ContentBlock(text = "Namun lantaran kondisi telah memasuki malam hari dengan tingkat pencahayaan yang sangat terbatas, serta mempertimbangkan faktor keselamatan personel yang bertugas, kegiatan pemadaman dihentikan sementara dan akan dilanjutkan kembali pada Rabu, 3 Juni 2026, pagi.", imageUrl = null)
            )
        ),
        // Real from Tempo gali (politik, deep related) - rich 12 blocks example
        Article(
            id = "politik-bgn-2143277",
            title = "Pimpinan BGN Dirombak, Istana Minta Nanik Segera Konsolidasi",
            description = "ISTANA Kepresidenan meminta tiga petinggi baru Badan Gizi Nasional untuk segera melakukan konsolidasi dan memperkuat koordinasi lintas sektor.",
            thumbnailUrl = "https://statik.tempo.co/data/2026/02/18/id_1459552/1459552_720.jpg",
            source = "tempo.co",
            author = "Redaksi tempo.co",
            publishedAt = Instant.parse("2026-06-02T14:24:00.031264Z"),
            category = Category.POLITIK,
            readTimeMinutes = 4,
            url = "https://www.tempo.co/politik/pimpinan-bgn-dirombak-istana-minta-nanik-segera-konsolidasi-2143277",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/02/18/id_1459552/1459552_720.jpg"),
                ContentBlock(text = "Mensesneg Prasetyo Hadi di Gedung DPR, Jakarta, 18 Februari 2026. Tempo/Dian Rahma", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/02/18/id_1459552/1459552_720.jpg"),
                ContentBlock(text = "ISTANA Kepresidenan meminta tiga petinggi baru Badan Gizi Nasional untuk segera melakukan konsolidasi dan memperkuat koordinasi lintas sektor. Presiden Prabowo Subianto baru saja merombak susunan pimpinan BGN—lembaga yang mengurus proyek andalannya, makan bergizi gratis alias MBG, pada Selasa, 2 Juni 2026.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/02/02/id_1456559/1456559_720.jpg"),
                ContentBlock(text = "Selain itu, pemerintah juga meminta ketiga pemimpin BGN untuk memperkuat koordinasi dengan pemerintah daerah, baik pemerintah provinsi maupun kabupaten/kota, hingga memastikan seluruh program Badan Gizi Nasional dapat tetap berjalan dengan sebaik-baiknya.", imageUrl = null)
            )
        )
    )
}





