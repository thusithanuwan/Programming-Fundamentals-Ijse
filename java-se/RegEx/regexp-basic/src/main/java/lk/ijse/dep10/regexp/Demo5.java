package lk.ijse.dep10.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo5 {
    public static void main(String[] args) {
        String text = "Hi my phone number is : 077-5123456. My land line is : 011-2345987";
        text += "\n His phone number is : 099-8978987";

        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(text);


        while(matcher.find()){   //Is pattern is in the text?
            int start = matcher.start(); // What is the starting point?
            int end = matcher.end(); // What is the end point?
            System.out.printf("Start = %s, End = %s\n", start, end);
            System.out.println(text.substring(start, end));

        }

    }
}
