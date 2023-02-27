
import java.io.File;

public class RenameFileDemo1 {
    public static void main(String[] args) {

        File oldFile = new File("/home/thusitha/Desktop/abc.xml");

        File newFile = new File(oldFile.getParentFile(), "pom.xml");

        oldFile.renameTo(newFile);

    }
}
