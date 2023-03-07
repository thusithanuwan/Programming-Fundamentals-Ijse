package lk.ijse.dep10;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Conversion4 {
    public static void main(String[] args) {
        Date sqldate = Date.valueOf("2020-10-91");
        Time sqlTime = Time.valueOf("10:14:34");
        Timestamp sqlTimeStamp = Timestamp.valueOf("2022-10-04 12:14:12");

        /*java.sql.Date -> java.time.LocalDate*/
        LocalDate localDate = sqldate.toLocalDate();

        /*java.sql.Time -> java.time.LocalTime*/
        LocalTime localTime = sqlTime.toLocalTime();

        /*java.sql.TimeStamp -> java.time.LocalTimeStamp*/
        LocalDateTime localDateTime = sqlTimeStamp.toLocalDateTime();

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        /* java.time.LocalDate -> java.sql.Date*/
        Date sqlDate2 = Date.valueOf(localDate);

        /* java.time.LocalTime -> java.sql.Time*/
        Time sqlTime2 = Time.valueOf(localTime);

        /* java.time.LocalTimeStamp -> java.sql.TimeStamp*/
        Timestamp sqlTimeStamp2 = Timestamp.valueOf(localDateTime);

    }
}
