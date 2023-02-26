package lk.ijse.dep10.io;

import java.io.File;

public class DeleteFileDemo1 {
    public static void main(String[] args) {

        File file = new File("/home/thusitha/Desktop/new.txt");

        if (file.delete()) {                       //We can Delete both files and directories using this method
            System.out.println("File is Deleted.");
        }
    }
}
