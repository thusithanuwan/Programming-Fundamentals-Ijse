package lk.ijse.dep10.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteDemo1 {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/thusitha/Desktop/dep10.ijse");
        if (file.exists()) {
            System.out.println("File already exist");
            return;
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        String something = "Hello World";

        byte[] bytes = something.getBytes();  // We can convert String to the byte array using getBytes() method

//        fileOutputStream.write(bytes[0]);   // We can write one by one too.

        fileOutputStream.write(bytes);

        fileOutputStream.close();
    }
}
