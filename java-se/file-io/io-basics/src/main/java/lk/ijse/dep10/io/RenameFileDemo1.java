package lk.ijse.dep10.io;


import java.io.File;

public class RenameFileDemo1 {
    public static void main(String[] args) {

        File Oldfile = new File("/home/thusitha/Desktop/thusitha.html");

        File newFile = new File(Oldfile.getParentFile(), "nuwan.html");

        if (Oldfile.renameTo(newFile)) {
            System.out.println("Renamed !");
        }




    }
}
