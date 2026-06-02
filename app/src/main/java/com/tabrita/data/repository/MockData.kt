package com.tabrita.data.repository

import com.tabrita.domain.model.Article
import com.tabrita.domain.model.Category
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
            content = "Jakarta — Pemerintah Indonesia resmi meluncurkan Program Transformasi Digital Nasional 2026 di Istana Negara, Selasa (14/1). Program ini bertujuan mempercepat digitalisasi layanan publik dan memberdayakan 22 juta UMKM untuk go digital dalam dua tahun ke depan.\n\nMenteri Komunikasi dan Informatika menyatakan bahwa target utama adalah menciptakan 5 juta talenta digital baru serta membangun infrastruktur 5G di 50 kota prioritas.\n\n\"Ini bukan sekadar program, ini adalah fondasi masa depan Indonesia yang lebih kompetitif,\" ujarnya dalam konferensi pers.\n\nPara pelaku UMKM akan mendapatkan pelatihan gratis, pendanaan seed, dan akses marketplace terintegrasi. Program ini juga menghadirkan Super App Pemerintahan yang menyatukan 147 layanan publik dalam satu platform.",
            imageUrl = "https://picsum.photos/id/1015/1200/800",
            source = "Kompas",
            author = "Rina Marlina",
            publishedAt = now.minus(2, ChronoUnit.HOURS),
            category = Category.POLITIK,
            readTimeMinutes = 6,
            url = "https://example.com/art001"
        ),
        Article(
            id = "art002",
            title = "Startup AI Lokal Raih Pendanaan Seri B Senilai Rp 450 Miliar",
            description = "Nusantara Intelligence menjadi startup AI pertama di Asia Tenggara yang mencapai valuasi unicorn.",
            content = "Bandung — Nusantara Intelligence, startup kecerdasan buatan asal Bandung, mengumumkan pendanaan Seri B senilai Rp 450 miliar dari konsorsium investor global dan lokal.\n\nPerusahaan ini dikenal dengan produk andalannya, \"BudiAI\", asisten virtual yang sudah digunakan lebih dari 3 juta pengguna di Indonesia untuk layanan customer service dan edukasi.\n\nCEO dan Co-founder, Andi Pratama, mengatakan dana tersebut akan digunakan untuk ekspansi ke 5 negara ASEAN dan pengembangan model bahasa besar (LLM) khusus Bahasa Indonesia dan daerah.\n\n\"Kami ingin AI yang memahami konteks budaya Nusantara, bukan hanya menerjemahkan,\" kata Andi.\n\nValuasi perusahaan kini mencapai 1,2 miliar USD, menjadikannya unicorn pertama di bidang AI dari Indonesia.",
            imageUrl = "https://picsum.photos/id/1018/1200/800",
            source = "Tech in Asia",
            author = "Dian Sastro",
            publishedAt = now.minus(5, ChronoUnit.HOURS),
            category = Category.TEKNOLOGI,
            readTimeMinutes = 5,
            url = "https://example.com/art002"
        ),
        Article(
            id = "art003",
            title = "Indeks Harga Saham Gabungan Tembus 8.200, Rekor Baru Pasar Modal",
            description = "Investor asing kembali masuk deras seiring fundamental ekonomi yang solid dan suku bunga global yang stabil.",
            content = "Jakarta — Indeks Harga Saham Gabungan (IHSG) menutup perdagangan hari ini di level 8.247, naik 1,8 persen dan mencetak rekor tertinggi sepanjang sejarah.\n\nSektor perbankan dan teknologi memimpin penguatan dengan saham BBCA, TLKM, dan GOTO menjadi top gainers. Volume transaksi mencapai Rp 18,4 triliun.\n\nAnalis dari Mandiri Sekuritas menyebutkan bahwa aliran dana asing yang masuk mencapai Rp 3,2 triliun dalam sepekan terakhir. Sentimen positif datang dari data inflasi yang terkendali dan proyeksi pertumbuhan ekonomi 5,3 persen tahun ini.\n\n\"Pasar sedang dalam fase risk-on yang sehat. Namun investor tetap disarankan berhati-hati terhadap volatilitas global,\" ujarnya.",
            imageUrl = "https://picsum.photos/id/1033/1200/800",
            source = "Bisnis Indonesia",
            author = "Farhan Maulana",
            publishedAt = now.minus(8, ChronoUnit.HOURS),
            category = Category.BISNIS,
            readTimeMinutes = 4,
            url = "https://example.com/art003"
        ),
        Article(
            id = "art004",
            title = "Timnas Indonesia Lolos ke Piala Asia 2027 setelah Kalahkan Australia",
            description = "Sejarah baru tercipta. Garuda Muda berhasil mengalahkan Socceroos 2-1 di kandang sendiri.",
            content = "Jakarta — Pertandingan kualifikasi Piala Asia 2027 di Stadion Utama Gelora Bung Karno berlangsung dramatis. Timnas Indonesia berhasil mengalahkan Australia 2-1 berkat gol indah dari Marselino Ferdinan di menit ke-71 dan sundulan Elkan Baggott di injury time.\n\nPelatih Shin Tae-yong menyebut kemenangan ini sebagai hasil kerja keras selama 18 bulan terakhir. \"Anak-anak sudah menunjukkan mental juara. Ini baru permulaan,\" katanya usai pertandingan.\n\nLebih dari 70.000 penonton memadati GBK, rekor tertinggi untuk pertandingan kualifikasi di Indonesia. Suporter menyanyikan \"Indonesia Raya\" dengan penuh semangat.\n\nIndonesia kini memuncaki grup dengan 13 poin dari 5 laga dan dipastikan lolos ke putaran final Piala Asia untuk pertama kalinya sejak 2007.",
            imageUrl = "https://picsum.photos/id/1005/1200/800",
            source = "DetikSport",
            author = "Rizky Pratama",
            publishedAt = now.minus(12, ChronoUnit.HOURS),
            category = Category.OLAHRAGA,
            readTimeMinutes = 7,
            url = "https://example.com/art004"
        ),
        Article(
            id = "art005",
            title = "Film \"Petualangan Sherina 2\" Tembus 5 Juta Penonton dalam 10 Hari",
            description = "Film keluarga besutan Riri Riza kembali memecahkan rekor box office Indonesia.",
            content = "Jakarta — \"Petualangan Sherina 2\" resmi menjadi film Indonesia terlaris tahun 2026 setelah menembus 5,1 juta penonton dalam 10 hari pertama penayangan.\n\nFilm yang dibintangi Sherina Munaf dan Derby Romero ini menceritakan petualangan Sherina dewasa yang kini menjadi jurnalis investigasi. Kisahnya membawa penonton ke berbagai pelosok Indonesia dengan visual yang memukau.\n\nRiri Riza mengaku tidak menyangka respons masyarakat begitu luar biasa. \"Kami membuat film ini dengan hati. Ternyata banyak yang merindukan cerita anak Indonesia yang positif dan penuh petualangan,\" ujarnya.\n\nFilm ini juga sukses di Malaysia, Singapura, dan Brunei. Versi extended cut akan tayang di platform streaming mulai Maret.",
            imageUrl = "https://picsum.photos/id/106/1200/800",
            source = "CNN Indonesia",
            author = "Nadia Putri",
            publishedAt = now.minus(1, ChronoUnit.DAYS),
            category = Category.HIBURAN,
            readTimeMinutes = 4,
            url = "https://example.com/art005"
        ),
        Article(
            id = "art006",
            title = "BPOM Setujui Terapi Gen Baru untuk Pengobatan Kanker Darah",
            description = "Terobosan medis yang memberikan harapan baru bagi pasien leukemia di Indonesia.",
            content = "Jakarta — Badan Pengawas Obat dan Makanan (BPOM) resmi memberikan persetujuan penggunaan terapi CAR-T Cell untuk pengobatan leukemia limfoblastik akut pada anak dan dewasa.\n\nTerapi ini menggunakan sel imun pasien yang dimodifikasi secara genetik untuk menyerang sel kanker. Tingkat keberhasilan pada uji klinis mencapai 82 persen remisi total.\n\nProf. Dr. dr. Aru Sudoyo, pakar hematologi dari RS Cipto Mangunkusumo, menyatakan bahwa ini adalah momen bersejarah bagi onkologi Indonesia. \"Dulu pasien harus ke luar negeri. Sekarang kita bisa lakukan di Jakarta dengan standar internasional.\"\n\nPemerintah berencana menanggung sebagian biaya terapi melalui BPJS Kesehatan untuk kasus tertentu mulai tahun depan.",
            imageUrl = "https://picsum.photos/id/201/1200/800",
            source = "Tempo",
            author = "dr. Lintang Wibowo",
            publishedAt = now.minus(1, ChronoUnit.DAYS).minus(4, ChronoUnit.HOURS),
            category = Category.KESEHATAN,
            readTimeMinutes = 5,
            url = "https://example.com/art006"
        ),
        Article(
            id = "art007",
            title = "LAPAN Berhasil Luncurkan Satelit LAPAN-A5, Fokus Pemantauan Iklim",
            description = "Satelit buatan dalam negeri ini akan membantu mitigasi bencana dan perubahan iklim.",
            content = "Bogor — Lembaga Penerbangan dan Antariksa Nasional (LAPAN) berhasil meluncurkan satelit LAPAN-A5 dari Kennedy Space Center, Florida, menggunakan roket Falcon 9 milik SpaceX.\n\nSatelit observasi bumi ini dilengkapi sensor multispektral dengan resolusi 5 meter dan mampu mengirim data setiap 4 jam. Fokus utamanya adalah pemantauan deforestasi, kebakaran hutan, dan perubahan garis pantai akibat kenaikan muka air laut.\n\nKepala LAPAN menyatakan bahwa data dari satelit ini akan dibagikan secara terbuka kepada peneliti, pemerintah daerah, dan masyarakat melalui portal SatuData.\n\n\"Ini adalah bukti bahwa Indonesia mampu mandiri dalam teknologi antariksa,\" katanya.\n\nLAPAN-A5 adalah satelit keenam yang berhasil diluncurkan Indonesia dalam 8 tahun terakhir.",
            imageUrl = "https://picsum.photos/id/1006/1200/800",
            source = "Antara News",
            author = "Agus Setiawan",
            publishedAt = now.minus(2, ChronoUnit.DAYS),
            category = Category.SAINS,
            readTimeMinutes = 6,
            url = "https://example.com/art007"
        ),
        Article(
            id = "art008",
            title = "Gojek Luncurkan Fitur AI untuk Optimasi Rute dan Prediksi Permintaan",
            description = "Teknologi baru ini diprediksi dapat mengurangi waktu tunggu pengguna hingga 25 persen.",
            content = "Jakarta — Gojek mengumumkan peluncuran sistem AI prediktif terbaru bernama \"Gojek Brain 3.0\" yang akan mengoptimalkan penugasan driver dan memprediksi lonjakan permintaan di berbagai wilayah.\n\nDengan memanfaatkan data real-time dari jutaan perjalanan harian, sistem ini dapat memperkirakan permintaan 30 menit ke depan dengan akurasi 94 persen.\n\nHasil uji coba di Jabodetabek menunjukkan penurunan waktu tunggu rata-rata dari 4,2 menit menjadi 3,1 menit. Pengemudi juga mendapat peningkatan pendapatan rata-rata 18 persen karena penugasan yang lebih efisien.\n\n\"Kami ingin menciptakan ekosistem yang lebih adil bagi semua pihak,\" kata CEO Gojek dalam peluncuran virtual.\n\nFitur ini akan diluncurkan secara bertahap ke seluruh Indonesia dalam 3 bulan ke depan.",
            imageUrl = "https://picsum.photos/id/160/1200/800",
            source = "TechCrunch Indonesia",
            author = "Bima Sakti",
            publishedAt = now.minus(2, ChronoUnit.DAYS).minus(6, ChronoUnit.HOURS),
            category = Category.TEKNOLOGI,
            readTimeMinutes = 4,
            url = "https://example.com/art008"
        ),
        Article(
            id = "art009",
            title = "Kebijakan Baru: Cuti Melahirkan Diperpanjang Jadi 6 Bulan untuk PNS",
            description = "Pemerintah berharap kebijakan ini meningkatkan kesejahteraan keluarga dan partisipasi perempuan di dunia kerja.",
            content = "Jakarta — Presiden menandatangani Peraturan Pemerintah yang memperpanjang cuti melahirkan bagi Pegawai Negeri Sipil (PNS) perempuan dari 3 bulan menjadi 6 bulan penuh dengan gaji penuh.\n\nKebijakan ini juga memberikan hak cuti ayah selama 1 bulan penuh yang dapat diambil bersamaan atau terpisah.\n\nMenteri PANRB mengatakan kebijakan ini merupakan bagian dari reformasi birokrasi yang berorientasi pada keseimbangan kehidupan kerja dan keluarga (work-life balance).\n\n\"Kami ingin Indonesia menjadi negara yang ramah keluarga. Ibu yang bahagia akan melahirkan generasi yang lebih baik,\" ujarnya.\n\nKebijakan serupa juga didorong untuk sektor swasta melalui insentif pajak bagi perusahaan yang menerapkan standar cuti yang sama.",
            imageUrl = "https://picsum.photos/id/1009/1200/800",
            source = "Republika",
            author = "Siti Aminah",
            publishedAt = now.minus(3, ChronoUnit.DAYS),
            category = Category.POLITIK,
            readTimeMinutes = 3,
            url = "https://example.com/art009"
        ),
        Article(
            id = "art010",
            title = "EV Battery Plant Terbesar di Asia Tenggara Mulai Dibangun di Karawang",
            description = "Investasi Rp 28 triliun dari konsorsium Korea Selatan dan Indonesia.",
            content = "Karawang — Groundbreaking pabrik baterai kendaraan listrik terbesar di Asia Tenggara resmi dilakukan di Kawasan Industri Karawang, Jawa Barat.\n\nPabrik ini merupakan hasil kerja sama antara Hyundai Motor Group dan pemerintah Indonesia melalui Indonesia Battery Corporation. Kapasitas produksi direncanakan mencapai 30 GWh per tahun pada 2028.\n\nMenteri Investasi mengatakan proyek ini akan menciptakan 8.000 lapangan kerja langsung dan ribuan lapangan kerja tidak langsung. Selain itu, 40 persen komponen akan dipasok dari industri lokal.\n\n\"Ini adalah fondasi bagi ekosistem EV nasional. Indonesia tidak hanya ingin menjadi pasar, tapi juga produsen utama,\" tegasnya.\n\nPabrik ini ditargetkan mulai produksi komersial pada kuartal ke-4 2027.",
            imageUrl = "https://picsum.photos/id/133/1200/800",
            source = "Kontan",
            author = "Hendra Wijaya",
            publishedAt = now.minus(3, ChronoUnit.DAYS).minus(10, ChronoUnit.HOURS),
            category = Category.BISNIS,
            readTimeMinutes = 5,
            url = "https://example.com/art010"
        ),
        Article(
            id = "art011",
            title = "Prabowo Subianto Resmikan 15.000 Sekolah Unggulan di Seluruh Indonesia",
            description = "Program besar untuk pemerataan kualitas pendidikan dari Sabang sampai Merauke.",
            content = "Jakarta — Presiden Prabowo Subianto meresmikan program \"Sekolah Unggulan Indonesia\" yang akan membangun dan merevitalisasi 15.000 sekolah dasar dan menengah dalam 3 tahun ke depan.\n\nSetiap sekolah akan mendapatkan fasilitas laboratorium sains dan komputer, perpustakaan digital, lapangan olahraga standar, dan program makan siang bergizi gratis bagi seluruh siswa.\n\n\"Pendidikan adalah investasi termahal yang tidak akan pernah rugi. Anak Indonesia harus mendapatkan kesempatan yang sama di mana pun mereka lahir,\" kata Presiden dalam pidato peresmian di Istana Bogor.\n\nProgram ini juga melibatkan 120.000 guru yang akan mengikuti pelatihan intensif berbasis kompetensi abad 21.\n\nAnggaran total mencapai Rp 142 triliun yang bersumber dari APBN dan kerja sama internasional.",
            imageUrl = "https://picsum.photos/id/251/1200/800",
            source = "Media Indonesia",
            author = "Yusuf Kalla",
            publishedAt = now.minus(4, ChronoUnit.DAYS),
            category = Category.POLITIK,
            readTimeMinutes = 6,
            url = "https://example.com/art011"
        ),
        Article(
            id = "art012",
            title = "Tim Voli Putri Indonesia Juara AVC Challenge Cup 2026",
            description = "Kemenangan bersejarah atas Thailand di final yang berlangsung sengit.",
            content = "Manila — Tim voli putri Indonesia berhasil meraih gelar juara AVC Challenge Cup 2026 setelah mengalahkan Thailand 3-2 (25-23, 22-25, 25-21, 21-25, 15-12) di final yang berlangsung dramatis di Ninoy Aquino Stadium.\n\nMegawati Hangestri Pertiwi tampil gemilang dengan 28 poin, termasuk 4 ace servis. \"Ini kemenangan untuk seluruh rakyat Indonesia. Kami berjuang sampai titik darah penghabisan,\" kata Megawati usai pertandingan sambil meneteskan air mata.\n\nPelatih Maroko, Jorge Edson Souza de Brito, menyebut tim ini telah menunjukkan perkembangan luar biasa dalam 2 tahun terakhir. \"Mereka bukan lagi underdog. Mereka adalah tim yang harus ditakuti.\"\n\nIndonesia akan mewakili Asia Tenggara di FIVB Volleyball Challenger Cup nanti tahun ini.",
            imageUrl = "https://picsum.photos/id/180/1200/800",
            source = "Voli Indonesia",
            author = "Putri Ayu",
            publishedAt = now.minus(5, ChronoUnit.DAYS),
            category = Category.OLAHRAGA,
            readTimeMinutes = 4,
            url = "https://example.com/art012"
        ),
        Article(
            id = "art013",
            title = "Festival Musik Prambanan 2026 Hadirkan 40 Artis Internasional",
            description = "Event musik terbesar di Jawa kembali dengan line-up paling ambisius dalam sejarahnya.",
            content = "Yogyakarta — Setelah 3 tahun absen, Festival Musik Prambanan (FMP) kembali dengan skala yang jauh lebih besar. Tahun ini, 40 artis dari 12 negara akan tampil di pelataran Candi Prambanan selama 4 hari (20-23 Februari).\n\nHeadliner meliputi Coldplay, Tame Impala, dan dua bintang K-Pop BLACKPINK dan NewJeans. Line-up lokal juga sangat kuat dengan Hindia, Isyana Sarasvati, dan Juicy Luicy.\n\nPanitia mengklaim sudah menjual 120.000 tiket dalam 48 jam pertama penjualan. Tiket harian dan festival pass sudah habis terjual.\n\n\"Kami ingin menjadikan Prambanan sebagai salah satu festival musik paling ikonik di Asia,\" kata founder FMP.\n\nPemerintah DIY memberikan dukungan penuh termasuk penataan lalu lintas dan keamanan ekstra.",
            imageUrl = "https://picsum.photos/id/29/1200/800",
            source = "Rolling Stone Indonesia",
            author = "Raka Mahendra",
            publishedAt = now.minus(6, ChronoUnit.DAYS),
            category = Category.HIBURAN,
            readTimeMinutes = 5,
            url = "https://example.com/art013"
        ),
        Article(
            id = "art014",
            title = "Peneliti ITB Temukan Cara Baru Deteksi Dini Penyakit Jantung Lewat AI",
            description = "Algoritma baru mampu memprediksi risiko serangan jantung 5 tahun ke depan dengan akurasi 91 persen.",
            content = "Bandung — Tim peneliti dari Institut Teknologi Bandung (ITB) berhasil mengembangkan model AI yang dapat mendeteksi risiko penyakit jantung koroner hingga 5 tahun sebelum gejala muncul.\n\nModel ini menganalisis data EKG, tekanan darah, riwayat keluarga, dan gaya hidup pasien menggunakan deep learning. Uji coba pada 45.000 pasien menunjukkan sensitivitas 91 persen dan spesifisitas 87 persen.\n\nProf. Dr. Ir. Bambang Riyanto, ketua tim, mengatakan model ini akan diintegrasikan ke dalam aplikasi kesehatan pemerintah secara gratis. \"Deteksi dini adalah kunci. Dengan AI, kita bisa menyelamatkan jutaan nyawa.\"\n\nPenelitian ini telah dipublikasikan di jurnal Nature Medicine dan mendapat apresiasi dari komunitas kardiologi internasional.",
            imageUrl = "https://picsum.photos/id/312/1200/800",
            source = "DetikHealth",
            author = "dr. Maya Kusuma",
            publishedAt = now.minus(7, ChronoUnit.DAYS),
            category = Category.KESEHATAN,
            readTimeMinutes = 6,
            url = "https://example.com/art014"
        ),
        Article(
            id = "art015",
            title = "Indonesia Resmi Jadi Tuan Rumah Piala Dunia U-20 2027",
            description = "Keputusan FIFA membawa harapan besar bagi perkembangan sepak bola tanah air.",
            content = "Zurich — FIFA secara resmi mengumumkan bahwa Indonesia terpilih sebagai tuan rumah Piala Dunia U-20 2027. Pengumuman ini disampaikan langsung oleh Presiden FIFA Gianni Infantino dalam kongres tahunan di Zurich, Swiss.\n\nIndonesia mengalahkan kandidat kuat lain seperti Maroko dan Arab Saudi. Delapan stadion di 6 provinsi akan digunakan, termasuk renovasi besar-besaran Stadion Gelora Bung Karno dan Stadion Manahan.\n\nMenteri Pemuda dan Olahraga menyatakan kesiapan penuh. \"Ini adalah kesempatan emas untuk membangun ekosistem sepak bola yang berkelanjutan, bukan hanya event 3 minggu.\"\n\nPSSI sudah membentuk panitia nasional dan menargetkan 1,2 juta penonton selama turnamen. Timnas U-20 Indonesia otomatis lolos sebagai tuan rumah.",
            imageUrl = "https://picsum.photos/id/177/1200/800",
            source = "Goal Indonesia",
            author = "Fajar Sidik",
            publishedAt = now.minus(8, ChronoUnit.DAYS),
            category = Category.OLAHRAGA,
            readTimeMinutes = 5,
            url = "https://example.com/art015"
        )
    )
}
