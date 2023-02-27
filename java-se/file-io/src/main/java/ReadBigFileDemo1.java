
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ReadBigFileDemo1 {

    public static void main(String[] args) throws IOException {

        File file = new File("/home/thusitha/Desktop/Morbius.mp4");

        System.out.println(file.length());  // can get file size, but not works for directories.

        if (!file.exists()){
            System.out.println("No such file exists");
            return;
        }

        FileInputStream fis = new FileInputStream(file);

        while(true) {
            byte[] buffer = new byte[1024 * 1024 * 10]; // 1024byte = 1kb , use buffer to read large file.
            int read = fis.read(buffer);
            if (read == -1) break;
        }

        fis.close();
        System.out.println("Read!");

    }

}
}
