package lk.ijse.dep10.io;

import java.io.File;
import java.io.IOException;

public class TempFileDemo1 {
    public static void main(String[] args) throws IOException {

        File tempFile = File.createTempFile("ijse", "dep10");     // createTempFIle() method creat a temporary file in your temp file directory

        System.out.println(tempFile);


    }
}
