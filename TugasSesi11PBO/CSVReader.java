import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static void main(String[] args) {
        String csvFile = "students.csv"; // pastikan file ini ada di folder yang sama dengan file .java
        String line;
        String csvSplitBy = ",";
        int indeks = 0;
        int jumlahBaris = 0; // menghitung jumlah baris data (tidak termasuk header)

        System.out.println("NIM, NAMA, UMUR, PRODI");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                indeks++;
                if (indeks > 1) {
                    String[] student = line.split(csvSplitBy);
                    System.out.println(student[0] + ", " + student[1] + ", " + student[2] + ", " + student[3]);
                    jumlahBaris++; // tambah hitungan setiap baris data (bukan header)
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Menampilkan jumlah baris data dalam file students.csv
        System.out.println("\nJumlah baris data dalam students.csv: " + jumlahBaris);
    }
}
