import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ReadFileDemo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/thusitha/Downloads/back.png");

        if (!file.exists()) {                              //  We can check existence of
            System.out.println("Invalid file path");
            return;
        }

        FileInputStream fis = new FileInputStream(file);

        byte[] bytes = fis.readAllBytes();           //  We can read all the bytes using readAllBytes() method.
//        System.out.println(Arrays.toString(fis.readAllBytes()));

        fis.close();

        System.out.println(Arrays.toString(bytes));

        System.out.println(new String(bytes));     // We can convert byte[] to a string using new String() method.

    }
}
