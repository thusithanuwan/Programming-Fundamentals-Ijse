package lk.ijse.dep10;

import java.util.Date;

public class Conversion2 {
    public static void main(String[] args) {


        // Conversion done with nano seconds: further using getTime()

        /* java.util.Date -> java.sql.Date */
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        /* java.util.Date -> java.sql.Time */
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());


        /* java.util.Date -> java.sql.TimeStamp */
        java.sql.Timestamp sqlTimeStamp = new java.sql.Timestamp(utilDate.getTime());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        /* java.sql.Date -> java.util.Date */
        utilDate = new java.util.Date(sqlDate.getTime());

        /* java.sql.Time -> java.util.Date */
        utilDate = new java.util.Date(sqlTime.getTime());

        /* java.sql.TimeStamp -> java.util.Date */
        utilDate = new java.util.Date(sqlTimeStamp.getTime());




    }
}
