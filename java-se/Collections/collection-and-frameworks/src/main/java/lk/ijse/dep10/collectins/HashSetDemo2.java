package lk.ijse.dep10.collectins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HashSetDemo2 {
    public static void main(String[] args) {
        List<String> cityList = Arrays.asList("panadure", "Galle", "Kandy", "Matara", "Kandy");
        System.out.println(cityList.getClass());   // Both ArrayList and Linked list implement List
        HashSet<String> citySet = new HashSet<>(cityList);
        System.out.println("Duplicate Exist :" + (citySet.size() < cityList.size()));

        String something = "Hello, are there any duplicates here in this text ? Find any duplicate words if exist";

        String[] s = something.split("\\b\\s+");

        ArrayList<String> strings = new ArrayList<>();

        for (String s1 : s) {
            strings.add(s1);
        }



        HashSet<String> stringSet = new HashSet<>(strings);
        System.out.println(stringSet);
    }
}
