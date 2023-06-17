import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class ReadDemo3 {
    public static void main(String[] args) throws IOException {
        File file = new File("name.dep10");


        FileReader fileReader = new FileReader(file);
        char[] buffer = null;
        int read = 0;
        String something = "";

        while (true){
            buffer = new char[5];
            read = fileReader.read(buffer);
            System.out.println(read);
            System.out.println(Arrays.toString(buffer));
            if (read == -1) {
                break;
            }
            something += new String(buffer,0,read);
        }
        fileReader.close();
        System.out.println(something);



        // How to read large files

//        char[] chars = new char[(int) file.length()];
//        int read1 = fileReader.read(chars    );
//        something += new String(chars,0,read1);
//        fileReader.close();



    }
}
