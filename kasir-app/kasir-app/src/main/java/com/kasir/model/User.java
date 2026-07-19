package com.kasir.model;

/**
 * Class dasar (superclass) yang merepresentasikan pengguna sistem.
 * Menjadi induk (parent) bagi class Kasir sebagai contoh penerapan
 * konsep Inheritance.
 */
public class User {

    private int id;
    private String username;
    private String namaLengkap;

    public User() {
    }

    public User(int id, String username, String namaLengkap) {
        this.id = id;
        this.username = username;
        this.namaLengkap = namaLengkap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    /**
     * Method yang akan di-override oleh subclass (Polymorphism).
     */
    public void tampilInfo() {
        System.out.println("=== Info User ===");
        System.out.println("ID       : " + id);
        System.out.println("Username : " + username);
        System.out.println("Nama     : " + namaLengkap);
    }
}
