public class Toshiba extends Laptop {

    @Override
    public void powerOn() {
        power = true;
        System.out.println("Toshiba menyala...");
    }

    @Override
    public void powerOff() {
        power = false;
        System.out.println("Toshiba mati...");
    }
}