package com.kasir.model;

import java.sql.Timestamp;

/**
 * Class model untuk data transaksi (header transaksi).
 */
public class Transaksi {

    private int idTransaksi;
    private Timestamp tanggal;
    private double total;

    public Transaksi() {
    }

    public Transaksi(int idTransaksi, Timestamp tanggal, double total) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.total = total;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return String.format("%-4d | %-20s | Rp%,.0f", idTransaksi, tanggal, total);
    }
}
