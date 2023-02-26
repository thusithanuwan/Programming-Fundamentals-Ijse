package lk.ijse.dep10.io;

import java.util.Map;

public class EnviromentDemo {
    public static void main(String[] args) {
        String path = System.getenv("PATH");
        System.out.println(path);

        Map<String, String> osEnvVariable = System.getenv();      //Map == Dictionary
        for (String key : osEnvVariable.keySet()) {
            System.out.printf("%s=%s\n", key, osEnvVariable.get(key));

        }

    }
}
