

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;


public class ReadFileDemo1 {

    public static void main(String[] args) throws IOException {

        File file = new File("/home/thusitha/Desktop/pic.jpg");

        if (!file.exists()) {                              //   Can check existence of a file.
            System.out.println("Invalid file path");
            return;
        }

        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[fis.available()];               // file.available() fives the size of the file.
        fis.read(bytes);

//        int read1 = fis.read();          // If needed, can read byte by byte.
//        int read2 = fis.read();
//        int read3 = fis.read();

        fis.close();   // Should close() streams after using.

        System.out.println(Arrays.toString(bytes));

        System.out.println(new String(bytes));
    }

}





