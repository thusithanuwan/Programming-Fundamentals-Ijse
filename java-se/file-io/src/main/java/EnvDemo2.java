
import java.io.File;
import java.util.Properties;

public class EnvDemo2 {
    public static void main(String[] args) {

        String osArchitecture = System.getProperty("os.arch");  // can get specific JavaFx Env. variable, if we know the key

        System.out.println(osArchitecture);

        System.setProperty("abc", "ijse");     // can set new JavFX Env. variable

        Properties properties = System.getProperties();

        properties.put("dep", "dep10");  // can also use put() method to set new JavaFx Env. variable

        for (Object key : properties.keySet()) {

            System.out.printf("%s=%s\n", key, properties.getProperty(key.toString()));
        }

        System.out.println("===============================================================================================");

        System.out.println(properties.getProperty("os.name"));

        properties.setProperty("os.name", "Windows");      // can also change value of a specific key.

        System.out.println(properties.getProperty("os.name"));

        properties.remove("os.name");        // can also remove variable

        System.out.println(properties.getProperty("os.name"));

        System.out.println("=============================================================================================");

        System.out.println(System.getProperty("user.home"));

        System.out.println("=============================================================================================");

        File desktopDir = new File(new File(System.getProperty("user.home")), "Desktop");

//        File desktop = new File(System.getProperty("user.home"), "Desktop");

        System.out.println(desktopDir);
//        System.out.println(desktop);

    }
}
