package com.kasir.model;

/**
 * Class model untuk data produk.
 * Seluruh atribut bersifat private (Encapsulation) dan diakses
 * melalui getter / setter.
 */
public class Produk {

    private int idProduk;
    private String namaProduk;
    private double harga;
    private int stok;

    public Produk() {
    }

    public Produk(int idProduk, String namaProduk, double harga, int stok) {
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stok = stok;
    }

    public Produk(String namaProduk, double harga, int stok) {
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.stok = stok;
    }

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return String.format("%-4d | %-25s | Rp%,-12.0f | %d",
                idProduk, namaProduk, harga, stok);
    }
}
