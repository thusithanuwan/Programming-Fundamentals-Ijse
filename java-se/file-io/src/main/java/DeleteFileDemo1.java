import java.io.File;

public class DeleteFileDemo1 {
    public static void main(String[] args) {

        File file = new File("/home/ranjith-suranga/Desktop/pom.xml");

        file.delete();     // Can delete both files and directories.
    }

}

