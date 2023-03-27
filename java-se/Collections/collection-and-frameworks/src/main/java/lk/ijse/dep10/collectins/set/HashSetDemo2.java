package lk.ijse.dep10.collectins.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HashSetDemo2 {
    public static void main(String[] args) {

        // Find Duplicate?

        List<String> cityList = Arrays.asList("Panadure", "Galle", "Kandy", "Matara", "Kandy");  // Arrays.asList() returns a fixed-size List


        System.out.println(cityList.getClass());   // Both ArrayList and Linked list implement List

        HashSet<String> citySet = new HashSet<>(cityList);

        System.out.println("Duplicate Exist :" + (citySet.size() < cityList.size()));

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        String something = "Hello, are there any duplicates here in this text ? Find any duplicate words if exist";

        String[] s = something.split("\\b\\s+");

        ArrayList<String> stringList = new ArrayList<>();

        for (String s1 : s) {
            stringList.add(s1);
        }

        HashSet<String> stringSet = new HashSet<>(stringList);
        System.out.println("Duplicate Exist : " + (stringSet.size() < stringList.size()));
    }
}
