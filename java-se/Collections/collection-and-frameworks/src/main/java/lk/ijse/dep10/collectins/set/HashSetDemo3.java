package lk.ijse.dep10.collectins.set;

import java.util.ArrayList;
import java.util.HashSet;

public class HashSetDemo3
{
    public static void main(String[] args) {
        // Find Duplicates?

        String something = "Find whether any duplicate characters exist in this text";

        char[] charArray = something.toCharArray();

        ArrayList<String> stringsSet = new ArrayList<>();
        for (char c : charArray) {
            if (!Character.isSpaceChar(c)) stringsSet.add(String.valueOf(c));
        }
        HashSet<String> charsSet = new HashSet<>(stringsSet);

        System.out.println("Duplicate Exist :" + (stringsSet.size() > charsSet.size()));




    }
}
