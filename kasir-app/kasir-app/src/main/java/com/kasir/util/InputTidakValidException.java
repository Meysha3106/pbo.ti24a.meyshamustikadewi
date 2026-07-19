package com.kasir.util;

/**
 * Exception khusus yang dilempar ketika input dari pengguna
 * tidak valid (misalnya angka negatif, format salah, dsb).
 */
public class InputTidakValidException extends Exception {

    public InputTidakValidException(String message) {
        super(message);
    }
}
