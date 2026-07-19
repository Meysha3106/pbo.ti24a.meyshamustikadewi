package com.kasir.util;

/**
 * Exception khusus untuk membungkus kesalahan yang berasal dari
 * proses koneksi atau operasi terhadap database.
 */
public class DatabaseException extends Exception {

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
