import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCopy {
    public static void main(String[] args) {
        String sourceFile = "students.csv";           // file CSV asal (folder yang sama dengan .java)
        String destinationFile = "students_copy.csv"; // file CSV tujuan (hasil salinan)

        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFile))) {

            String line;
            int jumlahBaris = 0;

            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                jumlahBaris++;
            }

            System.out.println("Berhasil menyalin " + jumlahBaris + " baris dari "
                    + sourceFile + " ke " + destinationFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
