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
            id = "tempo-jantung-2142479",
            title = "Keluhan Penyakit Jantung yang Sering Dianggap Biasa",
            description = "DOKTER Spesialis Jantung dan Pembuluh Darah Bethsaida Hospital Gading Serpong Putri Reno Indrisia mengatakan ada beberapa ciri pasien yang salah mengartikan gejala penyakit jantung.",
            thumbnailUrl = "https://statik.tempo.co/data/2025/02/04/id_1374609/1374609_720.jpg",
            source = "tempo.co",
            author = "Redaksi tempo.co",
            publishedAt = Instant.parse("2026-06-02T12:00:00Z"),
            category = Category.KESEHATAN,
            readTimeMinutes = 7,
            url = "https://www.tempo.co/gaya-hidup/keluhan-penyakit-jantung-yang-sering-dianggap-biasa-2142479",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/02/04/id_1374609/1374609_720.jpg"),
                ContentBlock(text = "Ilustrasi penyakit jantung/serangan jantung. Shutterstock", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/02/04/id_1374609/1374609_720.jpg"),
                ContentBlock(text = "DOKTER Spesialis Jantung dan Pembuluh Darah Bethsaida Hospital Gading Serpong Putri Reno Indrisia mengatakan ada beberapa ciri pasien yang salah mengartikan gejala penyakit jantung . \"Banyak pasien datang dalam kondisi terlambat karena gejala awal sering disalahartikan sebagai keluhan biasa,\" katanya dalam keterangan pers yang diterima Tempo pada awal Juni 2026. Beberapa ciri keluhan pasien penyakit jantung yang sering diabaikan pasien, seperti cepat lelah meski aktivitas ringan. Ada pula keluhan sesak napas saat berjalan atau naik tangga. Ciri lain lagi adalah nyeri dada yang terasa seperti tekanan atau tertahan. Ciri lainnya yang kerap dianggap angin lalu adalah jantung berdebar tidak teratur.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/02/04/id_1374719/1374719_720.jpg"),
                ContentBlock(text = "Bahkan beberapa orang menganggap hanya masuk angin, padahal sebenarnya dalam beberapa kasus adalah tanda awal penyakit jantung. Banyak orang baru sadar kondisi jantungnya bermasalah setelah gejala memburuk. \"Padahal, mengenali sinyal tubuh sejak dini bisa menjadi langkah penting untuk mencegah komplikasi yang lebih berat,\" katanya. Putri mengatakan tidak sedikit masyarakat yang baru memeriksakan diri ketika keluhan sudah mengganggu aktivitas atau bahkan memasuki fase darurat. \"Inilah yang membuat penyakit jantung kerap terlambat ditangani, meskipun gejala awal sebenarnya bisa dikenali lebih cepat,\" katanya menambahkan.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2022/10/18/id_1149887/1149887_720.jpg"),
                ContentBlock(text = "Perhimpunan Dokter Spesialis Kardiovaskular Indonesia (PERKI) menekankan pentingnya meningkatkan kesadaran masyarakat dalam pencegahan penyakit jantung dan pembuluh darah, sekaligus menyoroti bahwa beban penyakit jantung di Indonesia masih tinggi, salah satunya tercermin dari meningkatnya klaim pembiayaan penyakit jantung. Karena itu, deteksi dini gejala dan pengendalian faktor risiko menjadi langkah penting yang tidak boleh ditunda. Ketika gejala mulai muncul atau faktor risiko sudah dimiliki, langkah paling bijak adalah melakukan pemeriksaan menyeluruh.", imageUrl = null),
                ContentBlock(text = "Menurut Putri, ada beberapa pemeriksaan yang bisa dilakukan dari mulai pemeriksaan dengan CT Scan 512 Slice untuk pencitraan detail jantung, MRI 3 Tesla untuk pemeriksaan lanjutan, serta Ekokardiografi dan Treadmill test untuk menilai fungsi jantung secara menyeluruh. Selain itu, tersedia juga Cath Lab untuk angiografi koroner guna memvisualisasikan pembuluh darah jantung dan mendeteksi adanya penyempitan maupun sumbatan.", imageUrl = null),
                ContentBlock(text = "Jika hasil pemeriksaan menunjukkan adanya penyempitan atau sumbatan pembuluh darah, penanganan lanjutan perlu dilakukan secara tepat dan terukur agar risiko serangan jantung bisa dicegah sejak dini. Menurut Putri, di Heart & Vascular Center Bethsaida Hospital, tersedia berbagai tindakan jantung mulai dari Percutaneous Coronary Intervention (PCI) atau yang sering disebut dengan tindakan pemasangan ring jantung, Coronary Artery Bypass Grafting (CABG) atau operasi bypass jantung, penggunaan Drug Coated Balloon (DCB) atau balon berlapis obat, Minimally Invasive Cardiac Surgery (MICS) atau operasi jantung dengan sayatan kecil, hingga Endovenous Laser Ablation (EVLA) untuk kasus pembuluh darah tertentu.", imageUrl = null),
                ContentBlock(text = "Meski pilihan tindakan yang tersedia beragam, penanganan penyakit jantung tetap sangat bergantung pada kompleksitas kasus yang dialami pasien. Menurut Dokter Spesialis Jantung dan Pembuluh Darah Intervensi Bethsaida Hospital Serang Fani Suslina Hasibuan, kondisi pembuluh darah koroner pada pasien bisa sangat bervariasi. Pada kondisi tertentu, penyumbatan yang dialami pasien bisa lebih kompleks, seperti bifurkasi, yaitu sumbatan di area percabangan pembuluh darah, Chronic Total Occlusion (CTO), penyempitan yang menyebar di sepanjang pembuluh darah (diffuse disease), hingga sumbatan pada lebih dari satu pembuluh darah (multivessel disease).", imageUrl = null),
                ContentBlock(text = "Fani menjelaskan, pada kasus penyumbatan yang sudah mengeras atau berkapur (kalsifikasi berat), tindakan aterektomi, yaitu prosedur untuk membantu mengatasi plak atau sumbatan berkapur di pembuluh darah, sering diperlukan. Beberapa opsi penanganan yang dapat dilakukan seperti Rotablator (alat pengikis sumbatan kapur keras), Intravascular Lithotripsy (gelombang kejut pemecah kerak kapur), maupun Orbital Atherectomy System (alat putar pengikis plak keras) bisa digunakan sesuai dengan kondisi plak berdasarkan hasil imaging intrakoroner/pencitraan pembuluh darah jantung seperti IVUS (USG melihat dalam pembuluh darah) atau OCT (kamera laser detail dalam pembuluh).", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/02/04/id_1374609/1374609_720.jpg"),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2025/02/04/id_1374719/1374719_720.jpg"),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2022/10/18/id_1149887/1149887_720.jpg")
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
            id = "detik-ihsg-2026",
            title = "IHSG Ditutup Menguat, Dipimpin Saham Sektor Energi",
            description = "IHSG Bursa Efek Indonesia pada Selasa sore ditutup naik 68,04 poin atau 1,11 persen ke posisi 6.195,42 dipimpin sektor energi.",
            thumbnailUrl = "https://statik.tempo.co/data/2026/05/30/id_1475335/1475335_720.jpg",
            source = "detik.com",
            author = "Redaksi detik.com",
            publishedAt = Instant.parse("2026-06-02T12:00:00Z"),
            category = Category.BISNIS,
            readTimeMinutes = 6,
            url = "https://finance.detik.com/bursa/d-8515xx/ihsg-ditutup-menguat",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/05/30/id_1475335/1475335_720.jpg"),
                ContentBlock(text = "Pergerakan Indeks Harga Saham Gabungan (IHSG) di Bursa Efek Indonesia, Jakarta, 29 Mei 2026. Tempo/Ilham Balindra", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/05/30/id_1475335/1475335_720.jpg"),
                ContentBlock(text = "INDEKS Harga Saham Gabungan (IHSG) Bursa Efek Indonesia pada Selasa sore ditutup naik dipimpin oleh penguatan saham-saham sektor energi. IHSG ditutup menguat 68,04 poin atau 1,11 persen ke posisi 6.195,42. Sementara kelompok 45 saham unggulan atau indeks LQ45 naik 8,10 poin atau 1,33 persen ke posisi 619,27. \"IHSG menguat, para pelaku pasar juga mencerna data ekonomi,\" ujar Associate Director of Research and Investment Pilarmas Investindo Sekuritas Maximilianus Nico Demus alias Nico dalam kajiannya di Jakarta, Selasa, 2 Juni 2026, seperti dikutip Antara .", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/05/22/id_1474387/1474387_720.jpg"),
                ContentBlock(text = "Dari mancanegara, pelaku pasar tetap berhati-hati di tengah ketidakpastian seputar negosiasi Amerika Serikat (AS) dengan Iran, yang membuat pasar minyak mentah bergejolak. Di tengah ketidakpastian geopolitik yang berkelanjutan, laporan menunjukkan bahwa Teheran telah menangguhkan pembicaraan dengan Washington, sebagai tanggapan atas serangan Israel di Lebanon, meskipun Presiden AS Donald Trump menyatakan bahwa negosiasi masih berlangsung.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/05/18/id_1473769/1473769_720.jpg"),
                ContentBlock(text = "\"Pasar juga fokus perhatian pernyataan yang saling bertentangan dari Presiden AS Donald Trump dan Perdana Menteri Israel Benjamin Netanyahu mengenai diskusi baru-baru ini tentang konflik di Lebanon,\" ujar Nico. Dalam negeri, S&P Global melaporkan bahwa indeks Purchasing Managers' Index (PMI) naik dari sebelumnya 49,1 pada April 2026 menjadi 50,0 pada Mei 2026, menunjukkan dukungan sektor domestik yang kuat di tengah tekanan global berdampak pada volume ekspor. Sementara itu, Badan Pusat Statistik (BPS) melaporkan inflasi bulan Mei 2026 tercatat 0,28 persen month to month (mtm) dan sebesar 3,08 persen year on year (yoy), atau masih di rentang target Bank Indonesia (BI) berkisar 2,5 plus minus 1 persen.", imageUrl = null),
                ContentBlock(text = "Berdasarkan Indeks Sektoral IDX-IC, lima sektor menguat dipimpin sektor energi yang naik sebesar 1,78 persen, diikuti oleh sektor infrastruktur dan sektor barang baku yang masing-masing naik sebesar 0,64 persen dan 0,58 persen. Sedangkan enam sektor melemah yaitu sektor transportasi & logistik turun paling dalam sebesar 3,20 persen, diikuti oleh sektor kesehatan dan sektor properti yang masing-masing turun sebesar 2,65 persen dan 1,13 persen. Adapun saham-saham yang mengalami penguatan harga terbesar yaitu BEER, NZIA, KUAS, DSSA, dan BREN. Sedangkan saham-saham yang mengalami pelemahan harga terbesar, yakni TRUE, ELPI, APIC, KJEN dan EPAC.", imageUrl = null),
                ContentBlock(text = "Bursa saham regional Asia sore ini antara lain indeks Nikkei melemah 330,33 poin atau 0,49 persen ke 66.604,00, indeks Shanghai menguat 17,36 poin atau 0,43 persen ke 4.075,10, indeks indeks Hang Seng menguat 640,14 poin atau 2,52 persen ke 26.038,32, dan indeks Strait Times menguat 51,13 poin atau 1,01 persen ke 5.088,99. Pilihan Editor: IHSG Bisa Menguat Setelah Rebalancing MSCI. Apa Syaratnya?", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/05/30/id_1475335/1475335_720.jpg"),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/05/22/id_1474387/1474387_720.jpg"),
                ContentBlock(text = "", imageUrl = "https://statik.tempo.co/data/2026/05/18/id_1473769/1473769_720.jpg")
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
        ),
        // Real rich from detik (enriched full article) - 12 blocks example for BISNIS
        Article(
            id = "detik-allianz-2025",
            title = "Tumbuh 9,8%, Allianz Indonesia Kelola Dana Rp 43,7 T di 2025",
            description = "Sepanjang 2025, Allianz Indonesia mencatatkan total dana kelolaan (Asset Under Management) tumbuh 9,8 persen menjadi Rp 43,7 triliun.",
            thumbnailUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/allianz-indonesia-1780395840323_43.jpeg?w=210&q=90",
            source = "detik.com",
            author = "Redaksi detik.com",
            publishedAt = Instant.parse("2026-06-02T14:24:00.031264Z"),
            category = Category.BISNIS,
            readTimeMinutes = 5,
            url = "https://finance.detik.com/moneter/d-8514928/tumbuh-9-8-allianz-indonesia-kelola-dana-rp-43-7-t-di-2025",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/allianz-indonesia-1780395840246.jpeg?w=800"),
                ContentBlock(text = "Sepanjang 2025, Allianz Indonesia mencatatkan total dana kelolaan (Asset Under Management/AUM) sebesar Rp 43,7 triliun (termasuk dana kelolaan Allianz Life, Allianz Syariah, dan DPLK Allianz. Nilai ini tumbuh 9,8% secara YoY berdasarkan laporan keuangan perusahaan tahun 2025. Sepanjang tahun, Allianz Indonesia mengelola aset pada 49 jenis unit link fund. Tiga fund dengan dana kelolaan tertinggi sepanjang 2025 adalah Smartlink Equity (Rp 5,8 triliun), Smartlink Fixed Income (Rp 1,7 triliun), dan Smartlink Balanced (Rp 1,4 triliun). \"Di tengah volatilitas global sepanjang 2025, Allianz Indonesia tetap berfokus pada konsistensi pengelolaan dana kelolaan nasabah dengan pendekatan investasi yang disiplin dan adaptif, sejalan dengan karakteristik bisnis asuransi yang berorientasi jangka panjang,\" ungkap, Chief Investment Officer Allianz Life Indonesia, Ni Made Daryanti dalam keterangan tertulis, Selasa (2/6/2026).", imageUrl = null),
                ContentBlock(text = "\"Memasuki 2026, kami mempersiapkan strategi yang lebih selektif dengan menekankan kualitas aset dan pengelolaan risiko yang terukur, agar tetap selaras dengan tujuan keuangan jangka panjang nasabah,\" imbuhnya. Kondisi pasar 2025: Resiliensi domestik di tengah ketidakpastian global Allianz Indonesia mengungkap dinamika ekonomi dan pasar di Indonesia memasuki fase yang berbeda dibandingkan tahun-tahun sebelumnya. Di tengah ketidakpastian global yang masih berlangsung, perekonomian domestik tetap menunjukkan ketahanan, didukung konsumsi yang mulai stabil, inflasi yang terjaga, serta peran investor domestik yang semakin dominan dalam menopang pasar modal.", imageUrl = null),
                ContentBlock(text = "Bank Indonesia yang secara total memangkas suku bunga 125 bps. Dari sisi konsumsi, kebijakan bantuan sosial pada paruh akhir tahun turut membantu menjaga daya beli, dengan porsi realisasi yang meningkat pada kuartal terakhir 2025. Di pasar modal konvensional, IHSG menutup 2025 di 8.646,94 atau +22,13% sepanjang tahun. Sementara itu, pada pasar obligasi INDOBeX Government Index tumbuh +12,43% YoY, dengan arus investor asing yang juga tercatat masih masuk secara neto.", imageUrl = null),
                ContentBlock(text = "Secara global, dinamika pasar sepanjang 2025 diwarnai oleh ketidakpastian kebijakan dan volatilitas, khususnya di awal tahun. Kekhawatiran terkait kebijakan tarif perdagangan Amerika Serikat, tekanan inflasi, serta perlambatan ekonomi di sejumlah negara besar sempat menekan sentimen pasar. Memasuki paruh kedua tahun 2025, sentimen mulai membaik seiring meredanya sebagian tensi perdagangan dan langkah Federal Reserve yang menurunkan suku bunga sebanyak tiga kali sepanjang tahun.", imageUrl = null),
                ContentBlock(text = "Kebijakan fiskal tetap ekspansif, dengan penguatan agenda hilirisasi dan peningkatan belanja modal, termasuk proyeksi belanja modal infrastruktur dan transportasi yang naik sekitar 37% YoY menjadi Rp 156 triliun. Dukungan terhadap konsumsi juga diperkirakan berlanjut melalui program bantuan sosial yang, jika digabung dengan program makan bergizi gratis, dapat mencapai lebih dari Rp 500 triliun atau tumbuh 53% YoY.", imageUrl = null),
                ContentBlock(text = "Secara global, proyeksi pertumbuhan PDB dunia masih menunjukkan ketahanan, namun lanskap 2026 menuntut kewaspadaan karena visibilitas politik dan ekonomi yang berkurang. Selain tema suku bunga dan inflasi, pasar juga mencermati dinamika kebijakan perdagangan, serta peluang pertumbuhan dari sektor informasi dan komunikasi termasuk kecerdasan buatan (AI).", imageUrl = null),
                ContentBlock(text = "Dalam menghadapi pasar yang tetap dinamis, Allianz Indonesia tetap mengutamakan prinsip kehati-hatian dengan menjaga keseimbangan portofolio investasi sesuai karakteristik bisnis asuransi jiwa yang berorientasi jangka panjang. Penempatan investasi dilakukan secara selektif dengan pendekatan fundamental yang disiplin, disertai pengelolaan portofolio yang adaptif untuk memitigasi risiko dan menjaga stabilitas hasil investasi.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/allianz-indonesia-1780395840246.jpeg?w=800"),
                ContentBlock(text = "Atas kinerja dana kelolaan hingga tahun 2025, Allianz Life Indonesia meraih tujuh penghargaan pada Best Unit Link Award 2025 dari Investortrust & Infovesta untuk: Smartwealth Equity IndoGlobal Fund Kategori Saham IDR Konvensional Periode 5, 7, dan 10 Tahun, Smartwealth Equity Indoasia Fund(USD) Kategori Saham USD Periode 10 Tahun, Smartwealth Dollar Multi Asset Fund Kategori Campuran USD Periode 5 Tahun, Smartwealth Dollar US Bond Fund Kategori Pendapatan Tetap USD Periode 3 Tahun, dan GroupLink Money Market Fund Kategori Pasar Uang IDR Konvensional Periode 5 Tahun.", imageUrl = null),
                ContentBlock(text = "Kemudian di tahun 2026, Allianz Life Indonesia mendapatkan penghargaan pada Best Unit Link Award 2026 dari Investortrust & Infovesta, CNBC Indonesia Unitlink Awards 2026, Most Trusted Financial Brands Awards 2026 dari Investortrust, dan Media Asuransi Unitlink Awards 2026. Deretan penghargaan ini mencerminkan konsistensi pengelolaan dana dalam menghasilkan imbal hasil yang kompetitif dengan karakteristik risiko yang terjaga.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/allianz-indonesia-1780395840246.jpeg?w=800")
            )
        ),
        // Another rich detik example (14 blocks, 5 imgs) for POLITIK - from recent enrichment boost
        Article(
            id = "detik-banjir-dki-2026",
            title = "Atasi Banjir DKI, Pemprov Andalkan Pompa, Pengerukan & Normalisasi Sungai",
            description = "Pemerintah Provinsi (Pemprov) DKI Jakarta terus berupaya memerangi banjir dengan memperkuat infrastruktur pengendali banjir.",
            thumbnailUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/pemprov-dki-1780397216503_43.jpeg?w=210&q=90",
            source = "detik.com",
            author = "Redaksi detik.com",
            publishedAt = Instant.parse("2026-06-02T14:24:00.031264Z"),
            category = Category.POLITIK,
            readTimeMinutes = 5,
            url = "https://news.detik.com/berita/d-8514970/atasi-banjir-dki-pemprov-andalkan-pompa-pengerukan-normalisasi-sungai",
            contentBlocks = listOf(
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/pemprov-dki-1780397216448.jpeg?w=800"),
                ContentBlock(text = "Pemerintah Provinsi (Pemprov) DKI Jakarta terus berupaya memerangi banjir dengan memperkuat infrastruktur pengendali banjir.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/pemprov-dki-1780397216448.jpeg?w=800"),
                ContentBlock(text = "Pemprov DKI Jakarta memperkuat infrastruktur pengendali banjir dengan mengandalkan pompa, pengerukan, dan normalisasi sungai.", imageUrl = null),
                ContentBlock(text = "Pemprov DKI Jakarta juga menjalankan JakTirta Project 2025-2026 untuk percepatan penanganan banjir.", imageUrl = null),
                ContentBlock(text = "Menurut Pramono, Rumah Pompa Ancol dirancang untuk mempercepat pembuangan air ke laut.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/pemprov-dki-1780397216375.jpeg?w=800"),
                ContentBlock(text = "Pemprov DKI Jakarta juga mempercepat pengerukan sungai, kali, dan saluran air lainnya.", imageUrl = null),
                ContentBlock(text = "Selain pompa dan pengerukan, Pemprov DKI Jakarta menyiapkan normalisasi sungai secara bertahap.", imageUrl = null),
                ContentBlock(text = "\"Saat ini sedang dilakukan pembangunan dan revitalisasi tanggul sungai di beberapa titik,\" ujarnya.", imageUrl = null),
                ContentBlock(text = "\"Insyaallah nggak ada kendala dan tanggul itu bisa selesai 1-2 tahun ke depan,\" tambah Pramono.", imageUrl = null),
                ContentBlock(text = "Ia menyebut penanganan banjir tidak bisa dilakukan secara parsial, butuh pendekatan komprehensif.", imageUrl = null),
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/pemprov-dki-1780397216448.jpeg?w=800"),
                ContentBlock(text = "", imageUrl = "https://akcdn.detik.net.id/community/media/visual/2026/06/02/pemprov-dki-1780397216375.jpeg?w=800")
            )
        )
    )
}





