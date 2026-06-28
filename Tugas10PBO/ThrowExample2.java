// 7. Analisa
// Percobaan ini membandingkan 3 keluaran informasi error: getMessage() hanya teks pesan, 
// toString() menghasilkan nama class exception + pesan, sedangkan printStackTrace() 
// menampilkan seluruh runtutan baris jalannya error.

public class ThrowExample2 {
    public static void main(String[] args) {
        try {
            throw new Exception("Here's my Exception");
        } catch (Exception e) {
            System.out.println("Caught Exception");
            System.out.println("e.getMessage(): " + e.getMessage());
            System.out.println("e.toString(): " + e.toString());
            System.out.println("e.printStackTrace(): ");
            e.printStackTrace();
        }
    }
}