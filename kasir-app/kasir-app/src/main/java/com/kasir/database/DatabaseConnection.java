package com.kasir.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class utilitas untuk membuka koneksi ke database MySQL (db_kasir)
 * menggunakan JDBC.
 *
 * Sesuaikan HOST, PORT, USER, dan PASSWORD dengan konfigurasi MySQL
 * di komputer masing-masing.
 */
public class DatabaseConnection {

    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "db_kasir";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME
            + "?useSSL=false&serverTimezone=Asia/Jakarta&allowPublicKeyRetrieval=true";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    private DatabaseConnection() {
        // mencegah instansiasi, class ini hanya menyediakan method static
    }

    /**
     * Mengambil koneksi aktif ke database. Membuat koneksi baru jika
     * belum ada atau koneksi sebelumnya sudah tertutup.
     *
     * @return objek Connection yang siap digunakan
     * @throws SQLException jika koneksi ke database gagal
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC MySQL tidak ditemukan: " + e.getMessage());
            }
        }
        return connection;
    }

    /**
     * Menutup koneksi database jika masih terbuka.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }
}
