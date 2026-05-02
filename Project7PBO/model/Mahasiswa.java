package model;

public class Mahasiswa extends Person {
    private String nim;

    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", NIM: " + nim;
}
}