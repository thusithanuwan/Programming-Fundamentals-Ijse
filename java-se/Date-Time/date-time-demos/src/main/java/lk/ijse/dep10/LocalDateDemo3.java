package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateDemo3 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2022, 10, 6);
        LocalTime time = LocalTime.of(13, 45, 10);

        // Convert date and time to datetime.
        LocalDateTime localDateTime = date.atTime(time);
        System.out.println(localDateTime);

        // Convert date to datetime.
        LocalDateTime localDateTime1 = date.atStartOfDay();
        System.out.println(localDateTime1);


        LocalDateTime dateTime = LocalDateTime.of(2022, 10, 5, 12, 45);
        System.out.println(dateTime);

        //Localtime to date
        LocalDate localDate = dateTime.toLocalDate();
        System.out.println(localDate);

        //Localtime to time
        LocalTime localTime = dateTime.toLocalTime();
        System.out.println(localTime);
    }
}
