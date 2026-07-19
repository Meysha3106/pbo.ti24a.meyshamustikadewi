# Aplikasi Kasir Sederhana (CLI) — Java & MySQL

Sistem kasir berbasis Command Line Interface yang dibangun dengan Java (OOP penuh)
dan MySQL, lengkap dengan procedure, function, trigger, dan view.

## 1. Struktur Proyek

```
kasir-app/
├── pom.xml
├── sql/
│   └── db_kasir.sql              # skrip database lengkap
└── src/main/java/com/kasir/
    ├── model/
    │   ├── User.java
    │   ├── Kasir.java             # extends User
    │   ├── Produk.java
    │   ├── Transaksi.java
    │   └── DetailTransaksi.java
    ├── database/
    │   └── DatabaseConnection.java
    ├── util/
    │   ├── InputHelper.java
    │   ├── InputTidakValidException.java
    │   └── DatabaseException.java
    ├── service/
    │   ├── ProdukService.java
    │   ├── TransaksiService.java
    │   └── LaporanService.java
    └── main/
        └── Main.java               # menu CLI (switch-case)
```

## 2. Persiapan Database

1. Pastikan MySQL Server sudah berjalan.
2. Jalankan skrip berikut melalui MySQL client / Workbench:
   ```bash
   mysql -u root -p < sql/db_kasir.sql
   ```
   Skrip ini akan membuat database `db_kasir`, tabel, procedure
   (`tambah_produk`, `tambah_transaksi`), function
   (`hitung_total_penjualan`), trigger (`trg_kurangi_stok`), dan
   view (`v_laporan_penjualan`), beserta beberapa data produk contoh.

## 3. Konfigurasi Koneksi

Buka `src/main/java/com/kasir/database/DatabaseConnection.java` dan
sesuaikan `USER` dan `PASSWORD` dengan akun MySQL Anda (default: `root`
tanpa password).

## 4. Menjalankan Aplikasi

Dengan Maven:
```bash
mvn clean package
java -jar target/kasir-app-jar-with-dependencies.jar
```

Atau kompilasi manual (unduh mysql-connector-j-8.3.0.jar terlebih dahulu):
```bash
javac -d out -cp mysql-connector-j-8.3.0.jar $(find src -name "*.java")
java -cp out:mysql-connector-j-8.3.0.jar com.kasir.main.Main
```

## 5. Alur Penggunaan

1. **Kelola Produk** → tambah produk baru / lihat daftar produk.
2. **Transaksi Penjualan** → buat transaksi, pilih produk & jumlah;
   stok otomatis berkurang lewat trigger setelah item disimpan.
3. **Laporan** → lihat total penjualan (via function) atau rincian
   penjualan (via view `v_laporan_penjualan`).
