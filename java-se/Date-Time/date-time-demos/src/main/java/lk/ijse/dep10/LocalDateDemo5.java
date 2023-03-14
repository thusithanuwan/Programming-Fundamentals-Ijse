package lk.ijse.dep10;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class LocalDateDemo5
{
    public static void main(String[] args) {
        LocalDate d1 = LocalDate.of(2002, 10, 9);
        LocalDate d2 = LocalDate.of(2019, 9, 8);

        // Duration

        Duration diff = Duration.between(d1.atStartOfDay(),d2.atStartOfDay());  // Should convert to the date time
        System.out.println(diff.toDays());
        System.out.println(diff.toHours());
        System.out.println(diff.toMinutes());


        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");

        // Period

        Period diff1 = Period.between(d1,d2);
        System.out.println(diff1.getDays());
        System.out.println(diff1.getMonths());
        System.out.println(diff1.getYears());
    }
}
