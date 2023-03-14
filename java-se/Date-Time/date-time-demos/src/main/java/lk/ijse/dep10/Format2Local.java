package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Format2Local {
    public static void main(String[] args) {
        LocalDate localdate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localdate.toString());
        System.out.println(localTime.toString());
        System.out.println(localDateTime.toString());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        String formattedDate = localdate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String formattedTime = localTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        String formattedDateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss a"));

        System.out.println(formattedDate);
        System.out.println(formattedTime);
        System.out.println(formattedDateTime);

    }
}
