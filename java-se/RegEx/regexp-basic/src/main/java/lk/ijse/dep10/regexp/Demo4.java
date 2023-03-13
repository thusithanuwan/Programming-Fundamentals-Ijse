package lk.ijse.dep10.regexp;

import java.util.ArrayList;
import java.util.Arrays;

public class Demo4 {
    public static void main(String[] args) {
        String nic = "My nic is : 971234560V";

        //matches()
        System.out.println(nic.matches("\\d{9}[V|v]"));


        //replace()
        nic = nic.replaceFirst("\\d{9}[V|v]", "123");
        System.out.println(nic);

        //split()
        String someText = "I want to know your name.";
        String[] split = someText.split("\\b");
        System.out.println(Arrays.toString(split));





    }

}
