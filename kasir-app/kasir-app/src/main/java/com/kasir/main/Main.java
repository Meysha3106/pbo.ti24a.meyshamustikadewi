package com.kasir.main;

import com.kasir.database.DatabaseConnection;
import com.kasir.model.Kasir;
import com.kasir.model.Produk;
import com.kasir.model.Transaksi;
import com.kasir.service.LaporanService;
import com.kasir.service.ProdukService;
import com.kasir.service.TransaksiService;
import com.kasir.util.DatabaseException;
import com.kasir.util.InputHelper;
import com.kasir.util.InputTidakValidException;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point aplikasi kasir CLI.
 * Menampilkan menu interaktif dan mengarahkan ke masing-masing service.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final InputHelper input = new InputHelper(scanner);

    private static final ProdukService produkService = new ProdukService();
    private static final TransaksiService transaksiService = new TransaksiService();
    private static final LaporanService laporanService = new LaporanService();

    public static void main(String[] args) {
        // Contoh objek Kasir yang sedang bertugas (Object, Inheritance, Polymorphism)
        Kasir kasirAktif = new Kasir(1, "kasir01", "Meysha Mustika Dewi", "Pagi");

        System.out.println("=======================================");
        System.out.println("   APLIKASI KASIR SEDERHANA (CLI)");
        System.out.println("=======================================");
        kasirAktif.tampilInfo();

        boolean berjalan = true;
        while (berjalan) {
            try {
                tampilkanMenuUtama();
                int pilihan = input.bacaInt("Pilih menu: ");

                switch (pilihan) {
                    case 1:
                        menuKelolaProduk();
                        break;
                    case 2:
                        menuTransaksiPenjualan();
                        break;
                    case 3:
                        menuLaporan();
                        break;
                    case 4:
                        berjalan = false;
                        System.out.println("Terima kasih. Program selesai.");
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia. Coba lagi.");
                }

            } catch (InputTidakValidException e) {
                System.out.println("Input tidak valid: " + e.getMessage());
            } catch (DatabaseException e) {
                System.out.println("Terjadi kesalahan database: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan tak terduga: " + e.getMessage());
            }
        }

        DatabaseConnection.closeConnection();
        scanner.close();
    }

    private static void tampilkanMenuUtama() {
        System.out.println();
        System.out.println("========== MENU UTAMA ==========");
        System.out.println("1. Kelola Produk");
        System.out.println("2. Transaksi Penjualan");
        System.out.println("3. Laporan");
        System.out.println("4. Keluar");
        System.out.println("=================================");
    }

    // -------------------------------------------------------------
    // MENU 1 : KELOLA PRODUK
    // -------------------------------------------------------------
    private static void menuKelolaProduk() throws InputTidakValidException, DatabaseException {
        boolean kembali = false;
        while (!kembali) {
            System.out.println();
            System.out.println("----- Kelola Produk -----");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Lihat Produk");
            System.out.println("3. Kembali");
            int pilihan = input.bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tambahProduk();
                    break;
                case 2:
                    lihatProduk();
                    break;
                case 3:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        }
    }

    private static void tambahProduk() throws InputTidakValidException, DatabaseException {
        String nama = input.bacaTeks("Nama produk : ");
        double harga = input.bacaDouble("Harga       : ");
        int stok = input.bacaInt("Stok awal   : ");

        produkService.tambahProduk(nama, harga, stok);
        System.out.println("Produk berhasil ditambahkan.");
    }

    private static void lihatProduk() throws DatabaseException {
        List<Produk> daftar = produkService.lihatSemuaProduk();
        System.out.println();
        System.out.println("ID   | Nama Produk               | Harga           | Stok");
        System.out.println("---------------------------------------------------------------");
        if (daftar.isEmpty()) {
            System.out.println("Belum ada data produk.");
        } else {
            for (Produk p : daftar) {
                System.out.println(p);
            }
        }
    }

    // -------------------------------------------------------------
    // MENU 2 : TRANSAKSI PENJUALAN
    // -------------------------------------------------------------
    private static void menuTransaksiPenjualan() throws InputTidakValidException, DatabaseException {
        boolean kembali = false;
        while (!kembali) {
            System.out.println();
            System.out.println("----- Transaksi Penjualan -----");
            System.out.println("1. Tambah Transaksi");
            System.out.println("2. Lihat Riwayat Transaksi");
            System.out.println("3. Kembali");
            int pilihan = input.bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tambahTransaksi();
                    break;
                case 2:
                    lihatRiwayatTransaksi();
                    break;
                case 3:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        }
    }

    private static void tambahTransaksi() throws InputTidakValidException, DatabaseException {
        lihatProduk();

        int idTransaksi = transaksiService.buatTransaksiBaru();
        System.out.println("Transaksi baru dibuat dengan ID: " + idTransaksi);

        boolean tambahLagi = true;
        boolean adaItem = false;

        while (tambahLagi) {
            int idProduk = input.bacaInt("ID Produk yang dibeli : ");
            Produk produk = produkService.cariProdukById(idProduk);

            if (produk == null) {
                System.out.println("Produk dengan ID tersebut tidak ditemukan.");
            } else {
                int jumlah = input.bacaInt("Jumlah beli           : ");
                try {
                    transaksiService.tambahItemTransaksi(idTransaksi, idProduk, jumlah);
                    adaItem = true;
                    System.out.println(produk.getNamaProduk() + " x" + jumlah + " ditambahkan ke transaksi.");
                } catch (DatabaseException e) {
                    System.out.println("Gagal menambah item: " + e.getMessage());
                }
            }

            String lanjut = input.bacaTeks("Tambah item lagi? (y/n): ");
            tambahLagi = lanjut.equalsIgnoreCase("y");
        }

        if (adaItem) {
            System.out.println("Transaksi #" + idTransaksi + " berhasil disimpan. Stok produk telah diperbarui otomatis.");
        } else {
            System.out.println("Transaksi #" + idTransaksi + " tidak memiliki item.");
        }
    }

    private static void lihatRiwayatTransaksi() throws DatabaseException {
        List<Transaksi> daftar = transaksiService.lihatRiwayatTransaksi();
        System.out.println();
        System.out.println("ID   | Tanggal              | Total");
        System.out.println("---------------------------------------------------------------");
        if (daftar.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (Transaksi t : daftar) {
                System.out.println(t);
            }
        }
    }

    // -------------------------------------------------------------
    // MENU 3 : LAPORAN
    // -------------------------------------------------------------
    private static void menuLaporan() throws InputTidakValidException, DatabaseException {
        boolean kembali = false;
        while (!kembali) {
            System.out.println();
            System.out.println("----- Laporan -----");
            System.out.println("1. Total Penjualan");
            System.out.println("2. Tampilkan View Laporan");
            System.out.println("3. Kembali");
            int pilihan = input.bacaInt("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    double total = laporanService.totalPenjualan();
                    System.out.printf("Total seluruh penjualan: Rp%,.0f%n", total);
                    break;
                case 2:
                    List<String> laporan = laporanService.tampilkanViewLaporan();
                    System.out.println();
                    if (laporan.isEmpty()) {
                        System.out.println("Belum ada data laporan.");
                    } else {
                        laporan.forEach(System.out::println);
                    }
                    break;
                case 3:
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        }
    }
}
