// 5. Kode yang menyebabkan error
//e.getMessage() digunakan untuk mengambil string pesan error singkat (misal: / by zero).
//e.printStackTrace() digunakan untuk melacak alur eksekusi baris kode mana yang menyebabkan error terjadi 
// (sangat berguna untuk debugging).

public class Exception5 {
    public static void main(String[] args) {
        int bil = 10;
        try {
            System.out.println(bil / 0);
        } catch (ArithmeticException e) {
            System.out.println("Pesan error: ");
            System.out.println(e.getMessage());
            System.out.println("Info stack trace:");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ini menghandle error yang terjadi");
        }
    }
}