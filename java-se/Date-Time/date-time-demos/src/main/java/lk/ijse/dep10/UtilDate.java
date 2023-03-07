package lk.ijse.dep10;

import javax.xml.crypto.Data;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class UtilDate {
    public static void main(String[] args) {
        Date d1 = new Date();  // Represent date and time, when the line is executed.
        System.out.println(d1);


        Calendar calendar = Calendar.getInstance();
        calendar.clear(); // 1970-01-01
        calendar.set(2020,0,1);
//        calendar.set(2020, 0,1,0,0,0);
        Date d2 = calendar.getTime(); // Represent time, that posses when creating calendar instance. Here we use Calendar and use Util to get the time.
        System.out.println(d2);

        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);
        int month = calendar.get(Calendar.MONTH);
        System.out.println("Month " + month); // should reduce 1 before read the month.
        int date = calendar.get(Calendar.DATE);
        System.out.println("Date " + date);
        int hours = calendar.get(Calendar.HOUR);
        System.out.println("Hours " + hours);
        int minutes = calendar.get(Calendar.MINUTE);
        System.out.println("Minutes " + minutes);
        int seconds = calendar.get(Calendar.SECOND);
        System.out.println("Second " + seconds);
        int milliseconds = calendar.get(Calendar.MILLISECOND);
        System.out.println("Milliseconds " + milliseconds);

    }
}
