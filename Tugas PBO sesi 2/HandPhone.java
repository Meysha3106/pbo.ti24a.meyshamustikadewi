// Perbaikan code program yang salah pada N0. 4

public class HandPhone {
    String jenis_hp;
    int tahun_pembuatan;

    // 1: Urutan modifier diubah dari "String publik" menjadi "public void"
    // Method setter tidak perlu return value, jadi gunakan void
    public void setDataHP(String jenis_hp, int tahun_pembuatan) {
        this.jenis_hp = jenis_hp;                   // Tambah "this" agar tidak ambigu
        this.tahun_pembuatan = tahun_pembuatan;     // Tambah "this" agar tidak ambigu
    }

    // 2: Tambahkan return statement dan akses modifier
    public String getJenisHP() {
        return jenis_hp;                            // Wajib ada return karena return type String
    }

    // 3: Tambahkan return statement dan akses modifier
    public int getTahunPembuatan() {
        return tahun_pembuatan;                     // Wajib ada return karena return type int
    }

    // 4: Urutan modifier diubah dari "public static main void" menjadi "public static void main"
    public static void main(String[] args) {
        HandPhone hp = new HandPhone();
        hp.setDataHP("Samsung Galaxy", 2024);   // 5: Ganti variabel dengan nilai konkret
        System.out.println(hp.getJenisHP());                              // 6: Tambah System.out.println()
        System.out.println(hp.getTahunPembuatan());                       // 6: Tambah System.out.println()
    }

}
