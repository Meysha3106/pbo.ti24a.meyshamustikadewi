// 1. Kode yang salah
// Array a berukuran 5 (indeks 0-4). Mengakses a[5] memicu ArrayIndexOutOfBoundsException. 
// Blok try-catch berhasil menangkap error tersebut dan mencetak pesan alternatif.

public class Exception1 {

    public static void main(String[] args) {
        int a[]=new int[5];
        try
        {
            a[5]=100;
        }
        catch (Exception e)
        {
            System.out.println("Terjadi pelanggaran memory");
        }
    }
}   