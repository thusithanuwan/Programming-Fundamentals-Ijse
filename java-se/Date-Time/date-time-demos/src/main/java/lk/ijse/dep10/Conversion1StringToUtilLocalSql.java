package lk.ijse.dep10;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Conversion1 {
    public static void main(String[] args) throws ParseException {
        String strDate = "2022-10-01";
        String strTime = "10:45:45";
        String strDateTime = "2022-10-04 10:45:34";

        //java.util.Date

        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        Date d2 = new SimpleDateFormat("kk:mm:ss").parse(strTime);
        Date d3 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").parse(strDateTime);

        System.out.println("Date = " + d1);
        System.out.println("Time = " + d2);
        System.out.println("DateTime = " + d3);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        // LocalDate-LocalTime-LocalDateTime

        LocalDate ld1 = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime ld2 = LocalTime.parse(strTime, DateTimeFormatter.ofPattern("kk:mm:ss"));
        LocalDateTime ld3 = LocalDateTime.parse(strDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"));



        System.out.println("Date = " + ld1);
        System.out.println("Time = " + ld2);
        System.out.println("DateTime = " + ld3);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


        //java.sql.Date

        java.sql.Date sqlDate = java.sql.Date.valueOf(strDate);
        Time sqlTime = Time.valueOf(strTime);
        Timestamp sqlTimeStamp = Timestamp.valueOf(strDateTime);

        System.out.println("Date = " + sqlDate);
        System.out.println("Time = " + sqlTime);
        System.out.println("DateTime = " + sqlTimeStamp);

    }
}
