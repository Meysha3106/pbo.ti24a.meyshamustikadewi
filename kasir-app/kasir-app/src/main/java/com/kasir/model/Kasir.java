package com.kasir.model;

/**
 * Class Kasir merupakan turunan (subclass) dari User.
 * Contoh penerapan Inheritance dan Polymorphism (override tampilInfo()).
 */
public class Kasir extends User {

    private String shift;

    public Kasir() {
        super();
    }

    public Kasir(int id, String username, String namaLengkap, String shift) {
        super(id, username, namaLengkap);
        this.shift = shift;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    /**
     * Polymorphism: method tampilInfo() dari class User di-override
     * di sini untuk menambahkan informasi khusus kasir (shift kerja).
     */
    @Override
    public void tampilInfo() {
        System.out.println("=== Info Kasir ===");
        System.out.println("ID       : " + getId());
        System.out.println("Username : " + getUsername());
        System.out.println("Nama     : " + getNamaLengkap());
        System.out.println("Shift    : " + shift);
    }
}
