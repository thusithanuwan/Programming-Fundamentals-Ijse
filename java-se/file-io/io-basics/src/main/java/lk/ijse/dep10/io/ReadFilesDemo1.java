package lk.ijse.dep10.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFilesDemo1 {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/thusitha/Desktop/pom.xml");  // should be a absolute path (Start with forward slash)

        System.out.println(file.exists());  // to check, file is exist that location

        if (!file.exists()) {
            System.out.println("Invalid Path");
            return;
        }

        FileInputStream fileInputStream = new FileInputStream(file);

        System.out.println(fileInputStream.available());  // can check size of the file

        byte[] bytes = new byte[fileInputStream.available()];


        int read = fileInputStream.read(bytes);      // read() method return an integer value, which is represents the bytes that method read.

        System.out.println(read);
        System.out.println(new String(bytes));

        fileInputStream.close();   // Should close when we plug a stream to a file.


    }
}
