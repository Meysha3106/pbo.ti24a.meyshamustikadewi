// 3. Kode yang menyebabkan error
// Pembagian dengan nol memicu ArithmeticException. Aturan hierarki exception mewajibkan subclass 
// (ArithmeticException) diletakkan di atas superclass (Exception).

public class Exception3 {
    public static void main(String[] args) {
        int bil = 10;
        try {
            System.out.println(bil / 0);
        } catch (ArithmeticException e) {
            System.out.println("Terjadi Aritmatika error");
        } catch (Exception e) {
            System.out.println("Ini menghandle error yang terjadi");
        }
    }
}