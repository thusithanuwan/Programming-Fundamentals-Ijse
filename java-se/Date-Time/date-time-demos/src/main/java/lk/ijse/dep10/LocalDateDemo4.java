package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateDemo4 {
    public static void main(String[] args) {

        // Manipulation

        LocalDate today = LocalDate.now();

        //Add
        LocalDate localDate = today.plusDays(10);

        //Subtraction
        LocalDate localDate2 = today.minusDays(2);

        LocalDate localDate1 = today.minusMonths(2);

        // Before-After
        System.out.println(today.isBefore(localDate));
        System.out.println(today.isAfter(localDate1));

        LocalDate today2 = LocalDate.of(2023, Month.MARCH, 7);

        System.out.println(today == today2);

        System.out.println(today.isEqual(today2));

        System.out.println(today2);

    }
}
