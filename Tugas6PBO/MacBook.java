public class MacBook extends Laptop {

    @Override
    public void powerOn() {
        power = true;
        System.out.println("MacBook menyala...");
    }

    @Override
    public void powerOff() {
        power = false;
        System.out.println("MacBook mati...");
    }
}