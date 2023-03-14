package lk.ijse.dep10;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Conversion5UtilToLocal {
    public static void main(String[] args) {
        Date utilDate = new Date();

        /*java.util.Date -> java.time.LocalDate*/
        LocalDate localDate = new java.sql.Date(utilDate.getTime()).toLocalDate();

        /*java.util.Date -> java.time.LocalTime*/
        LocalTime localTime = new Time(utilDate.getTime()).toLocalTime();

        /*java.util.Date -> java.time.LocalDateTime*/
        LocalDateTime localDateTime = new Timestamp(utilDate.getTime()).toLocalDateTime();

        /*java.time.LocalDate -> java.util.Date*/
        Date d1 = new Date(java.sql.Date.valueOf(localDate).getTime());

        /*java.time.LocalTime -> java.util.Date*/
        Date d2 = new  Date(Time.valueOf(localTime).getTime());

        /*java.time.LocalDateTime -> java.util.Date*/
        Date d3 = Date.from(Timestamp.valueOf(localDateTime).toInstant());

    }
}
