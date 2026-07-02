import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
        String csvFile = "new_students.csv"; // akan dibuat di folder yang sama dengan file .java
        List<String> data = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Input Data Mahasiswa ===");
        System.out.print("Masukkan jumlah data yang ingin ditambahkan: ");
        int jumlahData = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < jumlahData; i++) {
            System.out.println("\nData ke-" + (i + 1) + ":");

            System.out.print("NIM   : ");
            String nim = scanner.nextLine();

            System.out.print("Nama  : ");
            String nama = scanner.nextLine();

            System.out.print("Umur  : ");
            String umur = scanner.nextLine();

            System.out.print("Prodi : ");
            String prodi = scanner.nextLine();

            // Gabungkan menjadi satu baris CSV
            String baris = nim + "," + nama + "," + umur + "," + prodi;
            data.add(baris);
        }

        scanner.close();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
            System.out.println("\nData berhasil disimpan ke " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
