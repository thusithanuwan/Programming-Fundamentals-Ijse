import java.io.File;
import java.io.IOException;

public class CreateFileDemo1 {
    public static void main(String[] args) throws IOException {

        File file = new File("/home/thusitha/Desktop/google.txt");


        if (file.createNewFile()) {                     // Should make pointer for particular file before creating.
            System.out.println("File is created !");
        }

    }
}
