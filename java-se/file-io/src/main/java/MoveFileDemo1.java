
import java.io.File;

public class MoveFileDemo1 {
    public static void main(String[] args) {

        File src = new File("/home/thusitha/Desktop/nuwan.html");

        File desktopFolder = src.getParentFile();

        File test = new File(desktopFolder, "test/abc/edf");

        test.mkdirs();   // Can use mkdirs() to make multiple directories instead of use mkdir().

        File target = new File(test, src.getName());   // Should make pointer to the target directory with the file name.

        src.renameTo(target);



    }
}
