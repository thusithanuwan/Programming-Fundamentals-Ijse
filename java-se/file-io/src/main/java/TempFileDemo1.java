
import java.io.File;
import java.io.IOException;


public class TempFileDemo1 {

    public static void main(String[] args) throws IOException {

        File tempFile = File.createTempFile("ijse", "dep10");  // Should need 3 character for prefix.

        System.out.println(tempFile);

    }
}
