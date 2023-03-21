package lk.ijse.dep10.collectins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HashSetDemo4
{
    public static void main(String[] args) {
        String something = "Find whether any duplicate characters exist in this text";
        char[] charArray = something.toCharArray();
//        List<char[]> chars = Arrays.asList(charArray);
        ArrayList<String> strings = new ArrayList<>();
        for (char c : charArray) {
            if (!Character.isSpaceChar(c)) strings.add(String.valueOf(c));
        }
        HashSet<String> chars1 = new HashSet<>(strings);

        System.out.println(strings.size());
        System.out.println(chars1.size());

    }
}
