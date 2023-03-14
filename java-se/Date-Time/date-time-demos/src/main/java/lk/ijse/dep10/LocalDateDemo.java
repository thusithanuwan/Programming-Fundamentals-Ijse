package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateDemo {
    public static void main(String[] args) {

        // Today's Date
        LocalDate today = LocalDate.now();
        System.out.println(today);

        //Today's Time
        LocalTime time = LocalTime.now();
        System.out.println(time);

        // Today's Time and Date
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        // Set time, date and dateTime
        LocalDate today1 = LocalDate.of(2020, 7, 12);
        System.out.println(today1);

        LocalTime time1 = LocalTime.of(15, 10, 10);
        System.out.println(time1);

        LocalDateTime dateTime1 = LocalDateTime.of(2000, Month.APRIL, 6, 13, 45);
        System.out.println(dateTime1);

    }
}
