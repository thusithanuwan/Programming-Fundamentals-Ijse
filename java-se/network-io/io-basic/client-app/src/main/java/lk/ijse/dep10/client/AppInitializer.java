package lk.ijse.dep10.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class AppInitializer {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.8.131", 5050);// lo, IP Address
        OutputStream os = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter Something :");
            String input = scanner.nextLine();
            os.write(input.getBytes());
        }

    }
}
