// 8. Analisa
// throws IOException pada methodB menandakan bahwa method tersebut berpotensi menghasilkan error I/O, 
// sehingga pemanggilnya (main) wajib membungkusnya dalam try-catch.
// Blok finally menjamin bahwa kode di dalamnya pasti dieksekusi, baik terjadi exception maupun tidak.

import java.io.*;

// Class ini TIDAK boleh pakai kata 'public' kalau digabung
class Test3 {
    public void methodA() {
        System.out.println("Method A");
    }
    public void methodB() throws IOException {
        System.out.println("Method B");
    }
}

// Hanya class yang memiliki main method ini yang pakai 'public'
public class Utama {
    public static void main(String[] args) {
        Test3 o = new Test3();
        o.methodA();
        try {
            o.methodB();
        } catch (Exception e) {
            System.out.println("Error di Method B");
        } finally {
            System.out.println("Ini selalu dicetak");
        }
    }
}