package lk.ijse.dep10.io;

import java.io.File;
import java.util.Properties;

public class EnviromentDemo2 {
    public static void main(String[] args) {
        String osArch = System.getProperty("os.arch");
        System.out.println(osArch);


        System.setProperty("abc", "ijse");    // if exist, change-- if not exist add


        Properties properties = System.getProperties();
        for (Object key : properties.keySet()) {
            System.out.printf("%s=%s\n",key,properties.getProperty(key.toString()));;
        }

        properties.put("dep","dep10");            // if exist change -- if not add

        System.out.println(properties.getProperty("os.name"));          // To get Value
        properties.setProperty("os.name","windows");                         // To set Value
        System.out.println(properties.getProperty("os.name"));

        properties.remove("os.name");       // To remove Value

        System.out.println(System.getProperty("user.home"));
        File desktopDir1 = new File(new File(System.getProperty("user.home")), "Desktop");
        File desktopDir = new File((System.getProperty("user.home")), "Desktop");
        System.out.println(desktopDir);
        System.out.println(desktopDir1);

    }
}
