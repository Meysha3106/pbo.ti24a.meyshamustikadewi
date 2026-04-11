class Bank {

    // 1) Transfer dalam bank yang sama
    void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " (Bank yang sama)");
    }

    // 2) Transfer ke bank berbeda
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan);
    }

    // 3) Transfer dengan berita
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        System.out.println("Transfer Rp" + jumlah + " ke rekening " + rekeningTujuan + " di bank " + bankTujuan);
        System.out.println("Berita: " + berita);
    }

    // Suku bunga default
    void sukuBunga() {
        System.out.println("Suku bunga standar adalah 3%");
    }

    // Bonus: biaya transfer (default)
    int hitungBiaya(String bankTujuan) {
        return 5000; // biaya standar
    }
}