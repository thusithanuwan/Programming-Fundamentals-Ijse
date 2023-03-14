package lk.ijse.dep10;

import java.util.Calendar;
import java.util.Date;

public class UtilDate3 {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2020,4,5);
        Date d1 = cal.getTime();
        cal.clear();
        cal.set(2020,3,8);
        Date d2 = cal.getTime();
        cal.clear();
        cal.set(2019,11,4);
        Date d3 = cal.getTime();
        cal.clear();
        cal.set(2019,11,4);
        Date d4 = cal.getTime();

//        cal.before();  // Works with last set date.
//        cal.after();
//        cal.equals();

        System.out.println(d1.before(d2));
        System.out.println(d1.after(d2));
        System.out.println(d2.before(d1));
        System.out.println(d3 == d4);
        System.out.println(d3.equals(d4));  // Should use equals() to check the quality.

    }
}
