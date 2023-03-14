package lk.ijse.dep10;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CalculateBirthDay {
    static String date;

    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//
//        do{
//            System.out.print("Enter Your BirthDay:");
//            date = scanner.nextLine().trim();
//        }
//        while (validation(date) == null || validation(date).after(new Date()) );{
//            System.out.print("Enter Your BirthDay:");
//            date = scanner.nextLine().trim();
//        }

        Date birthDay = validation("1995-07-12");

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();


        cal1.setTime(birthDay);
        cal2.setTime(new Date());
        System.out.println(cal1.getTime());
        System.out.println(cal2.getTime());











    }

    private static Date validation(String date){
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e){
            return null;
        }
    }
}
