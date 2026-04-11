class BankBCA extends Bank {

    @Override
    void sukuBunga() {
        System.out.println("Suku bunga Bank BCA adalah 4.5%");
    }

    @Override
    int hitungBiaya(String bankTujuan) {
        return 6500; // lebih mahal
    }
}