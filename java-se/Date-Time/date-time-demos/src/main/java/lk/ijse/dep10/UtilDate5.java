package lk.ijse.dep10;

import java.util.Calendar;
import java.util.Date;

public class UtilDate5 {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2023,10,14);
        calendar.add(Calendar.MONTH,-3); // To add give positive value
        Date d1 = calendar.getTime();
        System.out.println(d1);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(d1);
        Date d2 = calendar1.getTime();
        System.out.println(d2);
    }
}
