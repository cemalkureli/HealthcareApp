package com.example.healthcare.Database

data class Doktor (
    val name: String,
    val surname: String,
    val address: String,
    val experience: String,
    val phoneNumber: String,
    val  price: String,
)

val doctors1 = arrayOf(
    // Çocuk Doktorları
    Doktor("Dr. Ayşe", "Yılmaz", "Ana Cadde, İzmir", "10 Yıl", "+90 555 12 34","100₺"),
    Doktor("Dr. Mehmet", "Demir", "Elma Sokak, Tire", "5 Yıl", "+90 555 56 78","125₺"),
    Doktor("Dr. Zeynep", "Aydın", "Meşe Caddesi, Akyaka", "15 Yıl", "+90 555 90 12","150₺"),
    Doktor("Dr. Ahmet", "Kara", "Sarı Yol, Kızıltepe", "7 Yıl", "+90 555 23 45","175₺"),
    Doktor("Dr. Aylin", "Şahin", "Mavi Cadde, Konya", "12 Yıl", "+90 555 67 89","200₺")
)

val doctors2 = arrayOf(
    // Diyetisyenler
    Doktor("Dr. Ahmet", "Kaya", "Sarı Yol, Bursa", "8 Yıl", "+90 555 43 21","110₺"),
    Doktor("Dr. Esra", "Öztürk", "Kırmızı Sokak, Akyaka", "3 Yıl", "+90 555 87 65","120₺"),
    Doktor("Dr. Can", "Ergün", "Mor Cadde, Şirince", "12 Yıl", "+90 555 2109","130₺"),
    Doktor("Dr. Merve", "Akgün", "Yeşil Yol, Çankaya", "6 Yıl", "+90 555 54 32","140₺"),
    Doktor("Dr. Elif", "Güneş", "Turuncu Caddesi, Antalya", "9 Yıl", "+90 555 98 76","150₺")
)

val doctors3 = arrayOf(
    // Cildiyeciler
    Doktor("Dr. Aylin", "Aktaş", "Mavi Sokak, Karabük", "6 Yıl", "+90 555 67 89","130₺"),
    Doktor("Dr. Emre", "Yıldırım", "Yeşil Caddesi, Akyaka", "9 Yıl", "+90 555 34 56","140₺"),
    Doktor("Dr. Nazlı", "Özcan", "Turuncu Yol, Uzungöl", "4 Yıl", "+90 555 09 87","150₺"),
    Doktor("Dr. Cem", "Kurt", "Kırmızı Cadde, Gemlik", "10 Yıl", "+90 555 32 10","160₺"),
    Doktor("Dr. Meltem", "Yaman", "Sarı Sokak, Nevşehir", "7 Yıl", "+90 555 65 43","170₺")
)

val doctors4 = arrayOf(
    // Psikiyatristler
    Doktor("Dr. Ali", "Koç", "Pembe Sokak, Trabzon", "11 Yıl", "+90 555 76 54","300₺"),
    Doktor("Dr. Elif", "Sarıyer", "Mor Cadde, Assos (Behramkale)", "7 Yıl", "+90 555 32 10","400₺"),
    Doktor("Dr. Murat", "Şahin", "Turuncu Yol, Beymelek", "2 Yıl", "+90 555 65 43","500₺"),
    Doktor("Dr. İrem", "Kurtul", "Yeşil Caddesi, Beşiktaş", "5 Yıl", "+90 555 98 76","600₺"),
    Doktor("Dr. Mehmet", "Türkmen", "Kırmızı Sokak, Gaziantep", "8 Yıl", "+90 555 23 45","700₺")
)

val doctors5 = arrayOf(
    // Aile Hekimleri
    Doktor("Dr. Sevgi", "Kara", "Mavi Sokak, Diyarbakır", "14 Yıl", "+90 555 09 87","150₺"),
    Doktor("Dr. Caner", "Doğan", "Yeşil Caddesi, Çankaya", "1 Yıl", "+90 555 45 67","175₺"),
    Doktor("Dr. Ceren", "Yılmaz", "Sarı Yol, Uçhisar", "13 Yıl", "+90 555 7890","200₺"),
    Doktor("Dr. Emre", "Kurtuluş", "Turuncu Cadde, Kızıltepe", "4 Yıl", "+90 555 01 23","225₺"),
    Doktor("Dr. Ayşe", "Gül", "Kırmızı Sokak, Konya", "11 Yıl", "+90 555 34 56","250₺")
)

val doctors6 = arrayOf(
    // Diş Hekimleri
    Doktor("Dr. Deniz", "Turan", "Mavi Sokak, Trabzon", "5 Yıl", "+90 555 54 32","200₺"),
    Doktor("Dr. Nilüfer", "Korkmaz", "Yeşil Caddesi, Cunda Adası", "10 Yıl", "+90 555 87 65","225₺"),
    Doktor("Dr. İbrahim", "Kılıç", "Turuncu Yol, Uçhisar", "3 Yıl", "+90 555 21 09","250₺"),
    Doktor("Dr. Selin", "Yıldız", "Kırmızı Cadde, Bornova", "9 Yıl", "+90 555 65 43","275₺"),
    Doktor("Dr. Hasan", "Aydın", "Sarı Sokak, İstanbul", "6 Yıl", "+90 555 98 76","300₺")
)

val doctors7 = arrayOf(
    // Kardiyologlar
    Doktor("Dr. Ebru", "Özdemir", "Pembe Sokak, İstanbul", "12 Yıl", "+90 555 43 21","800₺"),
    Doktor("Dr. Selim", "Çelik", "Mor Cadde, Alacati", "4 Yıl", "+90 555 76 54","900₺"),
    Doktor("Dr. Seda", "Acar", "Turuncu Yol, Safranbolu", "9 Yıl", "+90 555 09 87","1000₺"),
    Doktor("Dr. Murat", "Kaya", "Yeşil Caddesi, Kadıköy", "6 Yıl", "+90 555 32 10","1100₺"),
    Doktor("Dr. Ayşe", "Kurtuluş", "Kırmızı Sokak, Mardin", "11 Yıl", "+90 555 65 43","1200₺")
)
