package lk.ijse.dep10.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFilesDemo2 {
    public static void main(String[] args) throws IOException {
        // Read psd File
        File file = new File("/home/thusitha/Pictures/3d-hardware-gears/5143019.psd");

        FileInputStream fis = new FileInputStream(file);

        byte[] bytes = fis.readAllBytes();    //  We can use readAllBytes() method to read entire file.

        System.out.println((char) bytes[0]);
        System.out.println((char) bytes[1]);
        System.out.println((char) bytes[2]);
        System.out.println((char) bytes[3]);

        fis.close();


    }
}
