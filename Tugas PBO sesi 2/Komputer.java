// Menjelaskan masing-masing bagian code sesuai nomor!
public class Komputer {              // <- 1
    String jenis_komputer;           // <- 2
    private String merk;             // <- 2

    public void setDataKomputer(String jenis, String merk) {    // <- 3
        jenis_komputer = jenis;
        this.merk = merk;
    }

    public String getJenis() {
        return jenis_komputer;      // <- 4
    }

    public String getMerk() {
        return merk;                // <- 5
    }

    public static void main(String[] args) {
        Komputer mykom = new Komputer();                            // <- 6
        mykom.setDataKomputer("LAPTOP", "MACBOOK");     // <- 7
        System.out.print(mykom.getJenis());                         // <- 8
        System.out.print(mykom.getMerk());                          // <- 8
    }
}
