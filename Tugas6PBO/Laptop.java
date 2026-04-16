public class Laptop {
    protected boolean power = false;
    protected int volume = 50;

    public void powerOn() {
        power = true;
        System.out.println("Laptop menyala");
    }

    public void powerOff() {
        power = false;
        System.out.println("Laptop mati");
    }

    public void volumeUp() {
        if (power) {
            volume++;
            System.out.println("Volume: " + volume);
        } else {
            System.out.println("Nyalakan laptop dulu!");
        }
    }

    public void volumeDown() {
        if (power) {
            volume--;
            System.out.println("Volume: " + volume);
        } else {
            System.out.println("Nyalakan laptop dulu!");
        }
    }
}