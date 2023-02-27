import java.io.File;

public class MoveFileDemo2 {
    public static void main(String[] args) {

        File src = new File("/home/thusitha/Desktop/nuwan.html");

        File target = new File("/home/thusitha/Videos/nuwan.html");

        if (src.renameTo(target)) {
            System.out.println("Moved!");
        }


    }
}
