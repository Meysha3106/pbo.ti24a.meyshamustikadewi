package main;

import java.util.Scanner;
import model.Mahasiswa;
import service.MahasiswaServiceImpl;

public class MainApp {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        MahasiswaServiceImpl<Mahasiswa> service = new MahasiswaServiceImpl<>();

        int pilihan;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tampilkan Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();
            input.nextLine(); // biar gak loncat

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nama: ");
                    String nama = input.nextLine();

                    System.out.print("Masukkan NIM: ");
                    String nim = input.nextLine();

                    Mahasiswa m = new Mahasiswa(nama, nim);
                    service.tambahMahasiswa(m);

                    System.out.println("Data berhasil ditambahkan!");
                    break;

                case 2:
                    System.out.println("\n=== Data Mahasiswa ===");
                    service.tampilkan();
                    break;

                case 3:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 3);

        input.close();
    }
}