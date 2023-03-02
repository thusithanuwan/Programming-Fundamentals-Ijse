import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReaderDemo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("something2.dep10");

        String something = "";

        FileInputStream fis = new FileInputStream(file);
        while (true){
            int readByte = fis.read();
            if (readByte == -1) {
                break;
            }
            System.out.println(readByte);
            char c = (char) readByte;
            something += c;
        }
        fis.close();
        System.out.println(something);

    }
}
