
import java.io.File;

public class ListFileDemo1 {

    public static void main(String[] args) {

        File downloadDir = new File("/home/thusitha/Downloads");

        System.out.println(downloadDir.isFile());        // Can check weather it is a file or directory.

        System.out.println(downloadDir.isDirectory());

        System.out.println("-----------------------------------------------------------------------------------");

        String[] list = downloadDir.list();            // list() method returns String[] of file names.
        for (String s : list) {
            System.out.println(s);
        }

        File[] files = downloadDir.listFiles();       // listFile() method return Files[] of pointers.

        for (File file : files) {
            System.out.println(file);
        }
    }

}

