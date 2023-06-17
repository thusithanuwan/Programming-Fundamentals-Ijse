import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriterDemo2 {
    public  static void main(String[] args) throws IOException {
        File file = new File("something2.dep10");
        String someText = "Hello World";

        FileOutputStream fos = new FileOutputStream(file);

        for (int i = 0; i < someText.length(); i++) {
                char c = someText.charAt(i);
                byte b = (byte) c;
                fos.write(b);

        }
        fos.close();
        System.out.println("Written!");
    }
}
