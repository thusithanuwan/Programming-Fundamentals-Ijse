package lk.ijse.dep10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Conversion3UtilToLocal {
    public static void main(String[] args) {

        Date utilDate = new Date();

        /* java.util.Date -> java.time.LocalDate*/
        LocalDate localDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        /* java.util.Date -> java.time.LocalTime*/
        LocalTime localTime = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

        /* java.util.Date -> java.time.LocalDateTime*/
        LocalDateTime localDateTime = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();


        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        /* java.time.LocalDate -> java.util.Date */
        Date d1 = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        /* java.time.LocalTime -> java.util.Date */
        Date d2 = Date.from(localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant());

        /* java.time.LocalDateTime -> java.util.Date */
        Date d3 = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());


    }
}
