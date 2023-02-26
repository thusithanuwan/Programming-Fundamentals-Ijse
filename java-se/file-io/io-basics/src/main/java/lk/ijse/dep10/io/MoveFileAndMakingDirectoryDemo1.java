package lk.ijse.dep10.io;

import java.io.File;

public class MoveFileAndMakingDirectoryDemo1 {
    public static void main(String[] args) {

        //Move & make new directory
        File toMove = new File("/home/thusitha/Desktop/nuwan.html");

        File parentFile = toMove.getParentFile();    // Get parent directory "Desktop"

        File newDirectory = new File(parentFile, "test/move");  // make new directories inside the parent directory

        newDirectory.mkdirs();   // Create Directories    // Different between mkdir() and mkdirs() ? interview question

        File target = new File("/home/thusitha/Desktop/test/move", toMove.getName());

        toMove.renameTo(target);


    }
}
