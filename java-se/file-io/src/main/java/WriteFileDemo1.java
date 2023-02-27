
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class WriteFileDemo1 {

    public static void main(String[] args) throws IOException {

        File file = new File("/home/thusitha/Desktop/dep10.ijse");

        if (file.exists()){
            System.out.println("File already exists");
            return;
        }

        FileOutputStream fos = new FileOutputStream(file);

        String something = "Hello, this is a new file!";

        byte[] bytes = something.getBytes();             // Can convert String to byte array using getBytes();

        fos.write(bytes);

//        fos.write(bytes,0,10);   //  Can limit reading limit.


        fos.close();


    }
}
