package lk.ijse.dep10.io;

import java.io.File;

public class MoveFileDemo1 {
    public static void main(String[] args) {

        File toMove = new File("/home/thusitha/Desktop/thusitha");

        System.out.println(toMove.exists());

        File target = new File("/home/thusitha/Videos", toMove.getName());

        toMove.renameTo(target);   // Use same method for both rename and move

//        boolean result = toMove.renameTo(target);
//        System.out.println(result);

    }
}
