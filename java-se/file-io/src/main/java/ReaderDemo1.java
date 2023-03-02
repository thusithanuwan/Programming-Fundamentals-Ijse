import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReaderDemo1 {
    public static void main(String[] args) throws IOException {
        File file = new File("something.dep10");

        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = fis.readAllBytes();
        fis.close();

        String someText = new String(bytes);
        System.out.println(someText);
    }
}
