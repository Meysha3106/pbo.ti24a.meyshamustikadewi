import java.sql.*;
import java.util.Scanner;

public class TokoRetailCLI {

    // ==== Konfigurasi Koneksi Database ====
    static final String URL = "jdbc:mysql://localhost:3306/toko_retail";
    static final String USER = "root";      // sesuaikan dengan username MySQL kamu
    static final String PASSWORD = "";      // sesuaikan dengan password MySQL kamu

    static Connection conn;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Membuka koneksi ke database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi ke database toko_retail berhasil!\n");

            boolean jalan = true;
            while (jalan) {
                tampilkanMenu();
                System.out.print("Pilihan : ");
                String pilihan = scanner.nextLine();

                switch (pilihan) {
                    case "1":
                        tampilSemuaData();
                        break;
                    case "2":
                        tambahData();
                        break;
                    case "3":
                        cariData();
                        break;
                    case "4":
                        ubahData();
                        break;
                    case "5":
                        hapusData();
                        break;
                    case "0":
                        jalan = false;
                        System.out.println("Terima kasih, program selesai.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!\n");
                }
            }

            conn.close();
            scanner.close();

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan koneksi database:");
            e.printStackTrace();
        }
    }

    // ==== Menampilkan Menu ====
    static void tampilkanMenu() {
        System.out.println("+----------------------------+");
        System.out.println("|      MENU TOKO RETAIL      |");
        System.out.println("+----------------------------+");
        System.out.println("  1. Tampil Semua Data");
        System.out.println("  2. Tambah Data");
        System.out.println("  3. Cari Data");
        System.out.println("  4. Ubah Data");
        System.out.println("  5. Hapus Data");
        System.out.println("  0. Keluar");
        System.out.println();
    }

    // ==== 1. Tampil Semua Data ====
    static void tampilSemuaData() {
        String sql = "SELECT * FROM tbl_barang ORDER BY kode_barang";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n+----------------------------------------------------------+");
            System.out.println("|                   DAFTAR BARANG TOKO RETAIL               |");
            System.out.println("+---+------+--------------------+----------+--------+");
            System.out.printf("| %-1s | %-4s | %-18s | %-8s | %-6s |%n", "#", "Kode", "Nama Barang", "Harga", "Stok");
            System.out.println("+---+------+--------------------+----------+--------+");

            int no = 1;
            boolean adaData = false;
            while (rs.next()) {
                adaData = true;
                System.out.printf("| %-1d | %-4s | %-18s | %-8d | %-6d |%n",
                        no++,
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("harga_barang"),
                        rs.getInt("stok_barang"));
            }
            System.out.println("+---+------+--------------------+----------+--------+");

            if (adaData) {
                System.out.println("Total: " + (no - 1) + " barang\n");
            } else {
                System.out.println("Belum ada data barang.\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ==== 2. Tambah Data ====
    static void tambahData() {
        System.out.println("\n=== Tambah Data Barang ===");
        System.out.print("Kode Barang    : ");
        String kode = scanner.nextLine();
        System.out.print("Nama Barang    : ");
        String nama = scanner.nextLine();
        System.out.print("Harga          : ");
        int harga = Integer.parseInt(scanner.nextLine());
        System.out.print("Stok           : ");
        int stok = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO tbl_barang (kode_barang, nama_barang, harga, stok) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kode);
            ps.setString(2, nama);
            ps.setInt(3, harga);
            ps.setInt(4, stok);
            ps.executeUpdate();
            System.out.println("Data berhasil ditambahkan!\n");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data (kode mungkin sudah ada).\n");
        }
    }

    // ==== 3. Cari Data ====
    static void cariData() {
        System.out.println("\n=== Cari Data Barang ===");
        System.out.print("Masukkan kode atau nama barang : ");
        String keyword = scanner.nextLine();

        String sql = "SELECT * FROM tbl_barang WHERE kode_barang LIKE ? OR nama_barang LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            boolean ditemukan = false;
            System.out.println("\nHasil Pencarian:");
            System.out.println("+------+--------------------+----------+--------+");
            while (rs.next()) {
                ditemukan = true;
                System.out.printf("| %-4s | %-18s | %-8d | %-6d |%n",
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("harga_barang"),
                        rs.getInt("stok_barang"));
            }
            System.out.println("+------+--------------------+----------+--------+");

            if (!ditemukan) {
                System.out.println("Data tidak ditemukan.");
            }
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ==== 4. Ubah Data ====
    static void ubahData() {
        System.out.println("\n=== Ubah Data Barang ===");
        System.out.print("Masukkan kode barang yang akan diubah : ");
        String kode = scanner.nextLine();

        String cekSql = "SELECT * FROM tbl_barang WHERE kode_barang = ?";
        try (PreparedStatement cekPs = conn.prepareStatement(cekSql)) {
            cekPs.setString(1, kode);
            ResultSet rs = cekPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Data dengan kode tersebut tidak ditemukan.\n");
                return;
            }

            System.out.println("Data ditemukan: " + rs.getString("nama_barang")
                    + " | Harga: " + rs.getInt("harga_barang") + " | Stok: " + rs.getInt("stok_barang"));

            System.out.print("Nama Barang baru : ");
            String nama = scanner.nextLine();
            System.out.print("Harga baru       : ");
            int harga = Integer.parseInt(scanner.nextLine());
            System.out.print("Stok baru        : ");
            int stok = Integer.parseInt(scanner.nextLine());

            String updateSql = "UPDATE tbl_barang SET nama_barang = ?, harga_barang = ?, stok = ? WHERE kode_barang = ?";
            try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                updatePs.setString(1, nama);
                updatePs.setInt(2, harga);
                updatePs.setInt(3, stok);
                updatePs.setString(4, kode);
                updatePs.executeUpdate();
                System.out.println("Data berhasil diubah!\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ==== 5. Hapus Data ====
    static void hapusData() {
        System.out.println("\n=== Hapus Data Barang ===");
        System.out.print("Masukkan kode barang yang akan dihapus : ");
        String kode = scanner.nextLine();

        String sql = "DELETE FROM tbl_barang WHERE kode_barang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kode);
            int baris = ps.executeUpdate();

            if (baris > 0) {
                System.out.println("Data berhasil dihapus!\n");
            } else {
                System.out.println("Data dengan kode tersebut tidak ditemukan.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
