public class Main {
    public static void main(String[] args) {

        // Objek bank umum
        Bank bank = new Bank();

        System.out.println("=== Method Overloading ===");
        bank.transferUang(100000, "123456789");
        bank.transferUang(200000, "987654321", "BCA");
        bank.transferUang(300000, "1122334455", "BNI", "Bayar utang");

        bank.sukuBunga();
        System.out.println("Biaya transfer: Rp" + bank.hitungBiaya("BCA"));

        System.out.println("\n=== Method Overriding ===");

        // Objek BNI
        Bank bni = new BankBNI();
        bni.sukuBunga();
        bni.transferUang(150000, "555555", "BNI");
        System.out.println("Biaya transfer BNI: Rp" + bni.hitungBiaya("BNI"));

        // Objek BCA
        Bank bca = new BankBCA();
        bca.sukuBunga();
        bca.transferUang(250000, "666666", "BCA");
        System.out.println("Biaya transfer BCA: Rp" + bca.hitungBiaya("BCA"));
    }
}