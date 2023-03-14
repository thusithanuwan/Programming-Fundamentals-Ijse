package lk.ijse.dep10;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDate2 {

    public static void main(String[] args) {
        String date = "2021-09-12 15:20";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // Look SimpleDateFormat
        try {
            Date utilDate = sdf.parse(date);
            System.out.println(utilDate);
        } catch (ParseException e) {
            System.out.println("Failed to convert to the Util Date.");
            e.printStackTrace();
        }


    }
}
