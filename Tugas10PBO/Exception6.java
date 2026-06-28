// 6. Kode yang salah
// Melempar exception secara sengaja menggunakan throw.

public class Exception6 {
    static void demo() {
        NullPointerException t = new NullPointerException("Coba Throw");
        throw t; // Melempar exception secara sengaja
        // System.out.println("Ini tidak lagi dicetak"); // Unreachable code (dihapus/dikomen)
    }

    public static void main(String[] args) {
        try {
            demo();
        } catch (NullPointerException e) {
            System.out.println("Ada pesan error: " + e);
        }
        System.out.println("Selesai");
    }
}