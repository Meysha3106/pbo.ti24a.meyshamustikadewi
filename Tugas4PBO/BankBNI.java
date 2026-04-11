class BankBNI extends Bank {

    @Override
    void sukuBunga() {
        System.out.println("Suku bunga Bank BNI adalah 4%");
    }

    @Override
    int hitungBiaya(String bankTujuan) {
        return 4000; // lebih murah
    }
}