package lk.ijse.dep10.io;

import java.io.File;

public class ListFilesDemo1 {
    public static void main(String[] args) {

        File downloadDir = new File("/home/thusitha/Downloads");

        System.out.println(downloadDir.isFile());               // isFile() and isDirectory() methods return a boolean value.
        System.out.println(downloadDir.isDirectory());

        String[] list = downloadDir.list();     // can get file "name" list

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        File[] filesList = downloadDir.listFiles();      // we can get "pointers"  to all files using listFiles() method.

        for (File file : filesList) {
            System.out.println(file);
        }
    }
}
