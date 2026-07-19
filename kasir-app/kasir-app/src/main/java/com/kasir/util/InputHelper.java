package com.kasir.util;

import java.util.Scanner;

/**
 * Class utilitas untuk membaca input dari pengguna melalui CLI
 * sekaligus melakukan validasi dasar.
 */
public class InputHelper {

    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String bacaTeks(String label) throws InputTidakValidException {
        System.out.print(label);
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            throw new InputTidakValidException("Input teks tidak boleh kosong.");
        }
        return value;
    }

    public int bacaInt(String label) throws InputTidakValidException {
        System.out.print(label);
        String value = scanner.nextLine().trim();
        try {
            int hasil = Integer.parseInt(value);
            if (hasil < 0) {
                throw new InputTidakValidException("Nilai angka tidak boleh negatif.");
            }
            return hasil;
        } catch (NumberFormatException e) {
            throw new InputTidakValidException("Input harus berupa bilangan bulat.");
        }
    }

    public double bacaDouble(String label) throws InputTidakValidException {
        System.out.print(label);
        String value = scanner.nextLine().trim();
        try {
            double hasil = Double.parseDouble(value);
            if (hasil < 0) {
                throw new InputTidakValidException("Nilai harga tidak boleh negatif.");
            }
            return hasil;
        } catch (NumberFormatException e) {
            throw new InputTidakValidException("Input harus berupa angka (harga).");
        }
    }
}
