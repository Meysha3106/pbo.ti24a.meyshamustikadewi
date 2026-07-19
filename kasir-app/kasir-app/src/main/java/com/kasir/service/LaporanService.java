package com.kasir.service;

import com.kasir.database.DatabaseConnection;
import com.kasir.util.DatabaseException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Service yang menangani laporan penjualan.
 * Menggunakan function hitung_total_penjualan() dan
 * view v_laporan_penjualan.
 */
public class LaporanService {

    /**
     * Memanggil function hitung_total_penjualan() di database untuk
     * mendapatkan total seluruh penjualan.
     */
    public double totalPenjualan() throws DatabaseException {
        String sql = "SELECT hitung_total_penjualan() AS total";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("total");
            }
            return 0;

        } catch (SQLException e) {
            throw new DatabaseException("Gagal menghitung total penjualan: " + e.getMessage(), e);
        }
    }

    /**
     * Mengambil data dari view v_laporan_penjualan dan mengembalikannya
     * sebagai daftar baris teks siap tampil.
     */
    public List<String> tampilkanViewLaporan() throws DatabaseException {
        String sql = "SELECT id_transaksi, tanggal, nama_produk, jumlah, subtotal, total_transaksi "
                + "FROM v_laporan_penjualan";
        List<String> baris = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String line = String.format(
                        "Transaksi #%-4d | %-19s | %-20s | Qty: %-3d | Subtotal: Rp%,-10.0f | Total: Rp%,.0f",
                        rs.getInt("id_transaksi"),
                        rs.getTimestamp("tanggal"),
                        rs.getString("nama_produk"),
                        rs.getInt("jumlah"),
                        rs.getDouble("subtotal"),
                        rs.getDouble("total_transaksi")
                );
                baris.add(line);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Gagal mengambil laporan penjualan: " + e.getMessage(), e);
        }
        return baris;
    }
}
