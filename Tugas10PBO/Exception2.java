// 2. Kode yang menyebabkan error
// Struktur try-catch diletakkan di dalam loop while. Ketika i mencapai 3, greetings[3] 
// tidak ada, memicu exception yang kemudian mereset kembali i menjadi 0.

public class Exception2 {
    
    public static void main(String[] args) {
        int i=0;
        String greeting[]={
            "Hello World!",
            "No, I mean it!",
            "HELLO WORLD!"
        };
        while(i<4)
        {
            try
            {
                System.out.println(greeting[i]);
                i++;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
                System.out.println("Resetting index value");
                i=0;
            }
        
        }
    }
}