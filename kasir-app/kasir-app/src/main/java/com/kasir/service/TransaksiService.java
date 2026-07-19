package com.kasir.service;

import com.kasir.database.DatabaseConnection;
import com.kasir.model.Transaksi;
import com.kasir.util.DatabaseException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Service yang menangani transaksi penjualan.
 *
 * Alur:
 *  1. Buat header transaksi baru (INSERT ke tabel transaksi, total = 0).
 *  2. Untuk setiap item yang dibeli, panggil procedure tambah_transaksi()
 *     yang akan menyimpan detail dan menambah total transaksi.
 *  3. Trigger trg_kurangi_stok pada database otomatis mengurangi stok
 *     produk setelah setiap detail tersimpan.
 */
public class TransaksiService {

    /**
     * Membuat header transaksi baru dan mengembalikan id_transaksi
     * yang baru dibuat (auto increment).
     */
    public int buatTransaksiBaru() throws DatabaseException {
        String sql = "INSERT INTO transaksi (total) VALUES (0)";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new DatabaseException("Gagal mendapatkan id_transaksi baru.");
            }

        } catch (SQLException e) {
            throw new DatabaseException("Gagal membuat transaksi: " + e.getMessage(), e);
        }
    }

    /**
     * Menambahkan satu item (detail) ke dalam transaksi dengan
     * memanggil stored procedure tambah_transaksi().
     * Procedure ini juga memvalidasi ketersediaan stok di sisi database.
     */
    public void tambahItemTransaksi(int idTransaksi, int idProduk, int jumlah) throws DatabaseException {
        String sql = "{CALL tambah_transaksi(?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, idTransaksi);
            stmt.setInt(2, idProduk);
            stmt.setInt(3, jumlah);
            stmt.execute();

        } catch (SQLException e) {
            throw new DatabaseException("Gagal menambah item transaksi: " + e.getMessage(), e);
        }
    }

    /**
     * Mengambil riwayat seluruh transaksi (SELECT).
     */
    public List<Transaksi> lihatRiwayatTransaksi() throws DatabaseException {
        String sql = "SELECT id_transaksi, tanggal, total FROM transaksi ORDER BY tanggal DESC";
        List<Transaksi> daftar = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transaksi t = new Transaksi(
                        rs.getInt("id_transaksi"),
                        rs.getTimestamp("tanggal"),
                        rs.getDouble("total")
                );
                daftar.add(t);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Gagal mengambil riwayat transaksi: " + e.getMessage(), e);
        }
        return daftar;
    }

    /**
     * Menghapus transaksi (beserta detailnya lewat ON DELETE CASCADE).
     */
    public void hapusTransaksi(int idTransaksi) throws DatabaseException {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTransaksi);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Gagal menghapus transaksi: " + e.getMessage(), e);
        }
    }
}
