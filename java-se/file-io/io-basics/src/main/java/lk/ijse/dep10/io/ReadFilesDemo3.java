package lk.ijse.dep10.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFilesDemo3 {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/thusitha/Desktop/pom.xml");  // should be a absolute path (Start with forward slash)
//        System.out.println(file.exists());  // to check, file is exist that location
        if (!file.exists()) {
            System.out.println("Invalid Path");
            return;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
//        byte[] bytes = new byte[fileInputStream.available()];       // can check size of the file
//        fileInputStream.read(bytes);

        byte[] bytes = fileInputStream.readAllBytes();      //  we can do it in one line instead of using 2 lines

//        int read1 = fileInputStream.read();        // Large files open piece by piece
//        int read2 = fileInputStream.read();
//        int read3 = fileInputStream.read();

        System.out.println(bytes.length);
//        System.out.println(Arrays.toString(bytes));
//        System.out.println((char) bytes[0]);
//        System.out.println((char) bytes[1]);
//        System.out.println((char) bytes[2]);
//        System.out.println((char) bytes[3]);
        System.out.println(new String(bytes));
        fileInputStream.close();
    }
}
