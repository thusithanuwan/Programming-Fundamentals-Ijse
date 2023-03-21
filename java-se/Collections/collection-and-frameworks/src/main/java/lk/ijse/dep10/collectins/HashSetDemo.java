package lk.ijse.dep10.collectins;

import java.util.HashSet;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<String> mySet = new HashSet<>();
        mySet.add("IJSE");
        mySet.add("ESOFT");
        mySet.add("SLIT");
        mySet.add("IJSE");
        mySet.add("KDU");
        mySet.add("KDU");
        mySet.add("KDU");
        mySet.add("KDU");

        System.out.println(mySet);

        System.out.println(mySet.size());

        System.out.println(mySet.contains("KDU"));

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");

        for (String s : mySet) {
            System.out.println(s);
        }


    }
}
