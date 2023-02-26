package lk.ijse.dep10.io;

import java.io.File;
import java.io.IOException;

public class CreateNewFileDemo1 {
    public static void main(String[] args) throws IOException {

        CreateNewFileDemo1 createNewFile = new CreateNewFileDemo1();

        File file = new File("/home/thusitha/Desktop/new.txt");
        if (file.createNewFile()) {
            System.out.println("File is created.");
        }

    }
}
