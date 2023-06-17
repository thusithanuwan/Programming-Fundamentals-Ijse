
import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static void main(String[] args) throws IOException {

        File file = new File("/home/thusitha/Desktop" + File.separator + "abc.mp4");  // File.separator gives file separator for a particular Operating System.

        System.out.println(file);

        File file0 = new File("/home/thusitha/Desktop","thusitha.txt");
        System.out.println(file0);

        File file1 = new File(new File("/home/thusitha/Desktop"), "abc.mp4");

        System.out.println(file1);

        System.out.println(file.getName()); // File name

        System.out.println(file.getAbsolutePath()); // File path (Pointer)

        file1.createNewFile();   // Creates new file called "abc.mp4" in the Desktop directory.


    }
}
