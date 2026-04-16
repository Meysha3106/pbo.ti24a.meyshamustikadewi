import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input Student
        System.out.print("Masukkan nama mahasiswa: ");
        String namaMhs = input.nextLine();
        System.out.print("Masukkan alamat mahasiswa: ");
        String alamatMhs = input.nextLine();

        Student s = new Student(namaMhs, alamatMhs);

        System.out.print("Jumlah mata kuliah mahasiswa: ");
        int jumlahMK = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahMK; i++) {
            System.out.print("Nama MK: ");
            String mk = input.nextLine();
            System.out.print("Nilai: ");
            int nilai = input.nextInt();
            input.nextLine();

            s.addCourseGrade(mk, nilai);
        }

        System.out.println("\nData Mahasiswa:");
        System.out.println(s);
        s.printGrades();
        System.out.println("Rata-rata: " + s.getAverageGrade());

        // Input Teacher
        System.out.print("\nMasukkan nama dosen: ");
        String namaDosen = input.nextLine();
        System.out.print("Masukkan alamat dosen: ");
        String alamatDosen = input.nextLine();

        Teacher t = new Teacher(namaDosen, alamatDosen);

        System.out.print("Jumlah mata kuliah dosen: ");
        int jumlahMKDosen = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahMKDosen; i++) {
            System.out.print("Nama MK: ");
            String mk = input.nextLine();
            t.addCourse(mk);
        }

        System.out.println("\nData Dosen:");
        System.out.println(t);

        input.close();
    }
}