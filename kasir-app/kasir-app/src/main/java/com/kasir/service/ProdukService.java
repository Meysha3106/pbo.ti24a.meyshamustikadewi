package com.kasir.service;

import com.kasir.database.DatabaseConnection;
import com.kasir.model.Produk;
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
 * Service yang menangani seluruh operasi terkait data produk.
 * Menggunakan procedure tambah_produk() untuk INSERT, dan query
 * biasa untuk SELECT / UPDATE / DELETE.
 */
public class ProdukService {

    /**
     * Menambah produk baru dengan memanggil stored procedure tambah_produk().
     */
    public void tambahProduk(String namaProduk, double harga, int stok) throws DatabaseException {
        String sql = "{CALL tambah_produk(?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, namaProduk);
            stmt.setDouble(2, harga);
            stmt.setInt(3, stok);
            stmt.execute();

        } catch (SQLException e) {
            throw new DatabaseException("Gagal menambah produk: " + e.getMessage(), e);
        }
    }

    /**
     * Mengambil seluruh data produk (SELECT).
     */
    public List<Produk> lihatSemuaProduk() throws DatabaseException {
        String sql = "SELECT id_produk, nama_produk, harga, stok FROM produk ORDER BY id_produk";
        List<Produk> daftarProduk = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produk p = new Produk(
                        rs.getInt("id_produk"),
                        rs.getString("nama_produk"),
                        rs.getDouble("harga"),
                        rs.getInt("stok")
                );
                daftarProduk.add(p);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Gagal mengambil data produk: " + e.getMessage(), e);
        }
        return daftarProduk;
    }

    /**
     * Mengambil satu produk berdasarkan id (dipakai saat transaksi).
     */
    public Produk cariProdukById(int idProduk) throws DatabaseException {
        String sql = "SELECT id_produk, nama_produk, harga, stok FROM produk WHERE id_produk = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduk);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Produk(
                            rs.getInt("id_produk"),
                            rs.getString("nama_produk"),
                            rs.getDouble("harga"),
                            rs.getInt("stok")
                    );
                }
                return null;
            }

        } catch (SQLException e) {
            throw new DatabaseException("Gagal mencari produk: " + e.getMessage(), e);
        }
    }

    /**
     * Memperbarui data produk (UPDATE).
     */
    public void updateProduk(Produk produk) throws DatabaseException {
        String sql = "UPDATE produk SET nama_produk = ?, harga = ?, stok = ? WHERE id_produk = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produk.getNamaProduk());
            stmt.setDouble(2, produk.getHarga());
            stmt.setInt(3, produk.getStok());
            stmt.setInt(4, produk.getIdProduk());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Gagal memperbarui produk: " + e.getMessage(), e);
        }
    }

    /**
     * Menghapus produk berdasarkan id (DELETE).
     */
    public void hapusProduk(int idProduk) throws DatabaseException {
        String sql = "DELETE FROM produk WHERE id_produk = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduk);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Gagal menghapus produk: " + e.getMessage(), e);
        }
    }
}
