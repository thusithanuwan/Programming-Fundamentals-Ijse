package lk.ijse.dep10.cutegirl;

import java.util.Scanner;

    public class AppInitializer {
        public AppInitializer() {
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Enter Your Phone Number:");
            String contact = scanner.nextLine();
            System.out.printf("I hacked your whatsApp contact %s", contact);
        }
    }

