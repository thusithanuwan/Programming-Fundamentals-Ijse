import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriterDemo1 {
    public static void main(String[] args) throws IOException {
        String something = "I want to write.";
        byte[] bytes = something.getBytes();

        File file = new File("writer1.dep10");

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
        System.out.println("Saved !");
    }
}
