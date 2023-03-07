package lk.ijse.dep10;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Format1 {
    public static void main(String[] args) {

        /*Util date format*/
        Date utilDate = new Date();
        System.out.println(utilDate.toString());
        String formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(utilDate);
        System.out.println(formattedDate);
        String formattedDate2 = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(utilDate);
        System.out.println(formattedDate2);

    }
}
