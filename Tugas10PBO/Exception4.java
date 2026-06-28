// 4. Kode yang menyebabkan error
// Di dalam blok try, error pertama yang ditemui (bil/0) akan langsung melempar eksepsi ke blok catch yang sesuai, 
// sehingga baris b[3] di bawahnya tidak akan sempat dieksekusi.

public class Exception4 {
    public static void main(String[] args) {
        int bil = 10;
        String b[] = {"a", "b", "c"};
        try {
            System.out.println(bil / 0); // Ini dieksekusi duluan dan crash
            System.out.println(b[3]);
        } catch (ArithmeticException e) {
            System.out.println("Terjadi Aritmatika error");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Melebihi jumlah array");
        } catch (Exception e) {
            System.out.println("Ini menghandle error yang terjadi");
        }
    }
}