package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateDemo2 {
    public static void main(String[] args) {
        String strDate = "2022-10-05";
//        LocalDate date = LocalDate.parse(strDate);

        //Convert String date to a date
        LocalDate date = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Year " + date.getYear());
        System.out.println("Month " + date.getMonth());
        System.out.println("Date " + date.getDayOfMonth());
        System.out.println(date);


        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


        String strTime = "15:13:10";
        // Convert String time to time
        LocalTime time = LocalTime.parse(strTime, DateTimeFormatter.ofPattern("kk:mm:ss"));
        System.out.println("Hours " + time.getHour());
        System.out.println("Minute " + time.getMinute());
        System.out.println("Seconds " + time.getSecond());
        System.out.println(time);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        String srtDateTime = "2022-10-05 10:15 AM";
        //Convert String dateTime to dateTime
        LocalDateTime dateTime = LocalDateTime.parse(srtDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"));
        System.out.println(dateTime);

    }
}
