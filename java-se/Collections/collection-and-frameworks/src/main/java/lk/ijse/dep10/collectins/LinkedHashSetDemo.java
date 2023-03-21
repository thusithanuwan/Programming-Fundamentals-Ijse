package lk.ijse.dep10.collectins;

import java.util.LinkedHashSet;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        LinkedHashSet<String> strings = new LinkedHashSet<>();
        strings.add("ijse");
        strings.add("ijse");
        strings.add("esoft");
        strings.add("kdu");
        strings.add("nibm");
        strings.add("kdu");

        System.out.println(strings);

    }
}
