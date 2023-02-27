
import java.util.Map;

public class EnvDemo1 {
    public static void main(String[] args) {

        String path = System.getenv("PATH");  // We can specify OS Env. using System.getenv().

        System.out.println(path);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        Map<String, String> osEnvVariables = System.getenv();   // We can get all the OS Env. too.
        // Map = Dictionary

        for (String key : osEnvVariables.keySet()) {

            System.out.printf("%s=%s\n", key, osEnvVariables.get(key));
        }

    }
}

