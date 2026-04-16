import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Laptop laptop;

        System.out.println("Pilih Laptop:");
        System.out.println("1. Toshiba");
        System.out.println("2. MacBook");
        System.out.print("Pilihan: ");
        int pilih = input.nextInt();
        input.nextLine();

        if (pilih == 1) {
            laptop = new Toshiba();
        } else {
            laptop = new MacBook();
        }

        String command;

        do {
            System.out.println("\nMenu:");
            System.out.println("ON  - Nyalakan");
            System.out.println("OFF - Matikan");
            System.out.println("UP  - Volume naik");
            System.out.println("DOWN - Volume turun");
            System.out.println("EXIT - Keluar");
            System.out.print("Masukkan perintah: ");

            command = input.nextLine();

            switch (command.toUpperCase()) {
                case "ON":
                    laptop.powerOn();
                    break;
                case "OFF":
                    laptop.powerOff();
                    break;
                case "UP":
                    laptop.volumeUp();
                    break;
                case "DOWN":
                    laptop.volumeDown();
                    break;
                case "EXIT":
                    System.out.println("Program selesai");
                    break;
                default:
                    System.out.println("Perintah tidak dikenal");
            }

        } while (!command.equalsIgnoreCase("EXIT"));

        input.close();
    }
}