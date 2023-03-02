import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WitterDemo3 {
    public static void main(String[] args) throws IOException {
        String name = "Thusitha Nuwan Bandara";
        File file = new File("name.dep10");

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(name);
        fileWriter.close();

        System.out.println("Name is Saved!");
    }
}
