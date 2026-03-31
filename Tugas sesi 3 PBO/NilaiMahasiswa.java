import java.util.Scanner;

public class NilaiMahasiswa {

    String nim;
    String nama;
    int nilai;
    String grade;

    NilaiMahasiswa(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        this.grade = tentukanGrade(nilai);
    }

    String tentukanGrade(int nilai) {
        if (nilai > 100 || nilai < 0) return "INVALID";
        else if (nilai >= 80) return "A";
        else if (nilai >= 70) return "B";
        else if (nilai >= 60) return "C";
        else if (nilai >= 50) return "D";
        else return "E";
    }

    boolean lulus() {
        return grade.equals("A") || grade.equals("B") || grade.equals("C");
    }

    void tampilkan() {
        System.out.println("NIM   : " + nim);
        System.out.println("Nama  : " + nama);
        System.out.println("Nilai : " + nilai);
        System.out.println("Grade : " + grade);
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan jumlah mahasiswa: ");
        int n = sc.nextInt();
        sc.nextLine();

        NilaiMahasiswa[] data = new NilaiMahasiswa[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Mahasiswa ke-" + (i + 1) + " ---");
            System.out.print("NIM   : ");
            String nim = sc.nextLine();
            System.out.print("Nama  : ");
            String nama = sc.nextLine();
            System.out.print("Nilai : ");
            int nilai = sc.nextInt();
            sc.nextLine();

            if (nilai < 0 || nilai > 100) {
                System.out.println("Input nilai anda salah");
                i--;
                continue;
            }

            data[i] = new NilaiMahasiswa(nim, nama, nilai);
        }

        System.out.println("\n========================================");

        // Tampilkan semua data
        for (NilaiMahasiswa m : data) {
            m.tampilkan();
        }

        // Hitung statistik
        int jumlahLulus = 0, jumlahTidakLulus = 0;
        int jumlahA = 0, jumlahB = 0, jumlahC = 0, jumlahD = 0, jumlahE = 0;
        String namaLulus = "", namaTidakLulus = "";
        String namaA = "", namaB = "", namaC = "", namaD = "", namaE = "";
        int totalNilai = 0;

        for (NilaiMahasiswa m : data) {
            totalNilai += m.nilai;
            if (m.lulus()) {
                jumlahLulus++;
                namaLulus += (namaLulus.isEmpty() ? "" : ", ") + m.nama;
            } else {
                jumlahTidakLulus++;
                namaTidakLulus += (namaTidakLulus.isEmpty() ? "" : ", ") + m.nama;
            }
            switch (m.grade) {
                case "A": jumlahA++; namaA += (namaA.isEmpty() ? "" : ", ") + m.nama; break;
                case "B": jumlahB++; namaB += (namaB.isEmpty() ? "" : ", ") + m.nama; break;
                case "C": jumlahC++; namaC += (namaC.isEmpty() ? "" : ", ") + m.nama; break;
                case "D": jumlahD++; namaD += (namaD.isEmpty() ? "" : ", ") + m.nama; break;
                case "E": jumlahE++; namaE += (namaE.isEmpty() ? "" : ", ") + m.nama; break;
            }
        }

        double rata = (double) totalNilai / n;

        System.out.println("Jumlah Mahasiswa           : " + n);
        System.out.println("Jumlah Mahasiswa yg Lulus  : " + jumlahLulus + " yaitu " + namaLulus);
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + jumlahTidakLulus + " yaitu " + namaTidakLulus);
        if (jumlahA > 0) System.out.println("Jumlah Mahasiswa dengan Nilai A = " + jumlahA + " yaitu " + namaA);
        if (jumlahB > 0) System.out.println("Jumlah Mahasiswa dengan Nilai B = " + jumlahB + " yaitu " + namaB);
        if (jumlahC > 0) System.out.println("Jumlah Mahasiswa dengan Nilai C = " + jumlahC + " yaitu " + namaC);
        if (jumlahD > 0) System.out.println("Jumlah Mahasiswa dengan Nilai D = " + jumlahD + " yaitu " + namaD);
        if (jumlahE > 0) System.out.println("Jumlah Mahasiswa dengan Nilai E = " + jumlahE + " yaitu " + namaE);

        // Format rata-rata seperti di soal
        System.out.print("Rata-rata nilai mahasiswa adalah : ");
        StringBuilder rumus = new StringBuilder();
        for (int i = 0; i < n; i++) {
            rumus.append(data[i].nilai);
            if (i < n - 1) rumus.append("+");
        }
        rumus.append("/").append(n).append(" = ").append(rata);
        System.out.println(rumus);

        sc.close();
    }
}