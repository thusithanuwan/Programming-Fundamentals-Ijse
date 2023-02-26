package lk.ijse.dep10.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadBigFilesDemo1 {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/thusitha/Desktop/8. Other Layouts.mp4");

        if (!file.exists()) {
            System.out.println("No Such a file in that Directory");
            return;
        }

        FileInputStream fileInputStream = new FileInputStream(file);

        while (true) {
            byte[] buffer = new byte[1024* 1024 * 10];        // 1024 byte = 1kb

//            System.out.println(Arrays.toString(buffer));    // How to read large file ----> make buffer and then read

            int read = fileInputStream.read(buffer);

//            System.out.println(read);

            if (read == -1) {
                break;
            }
        }
        fileInputStream.close();

        System.out.println("Done!");
    }
}
