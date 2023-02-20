package lk.ijse.dep10.app;

import com.sun.source.tree.ReturnTree;

import java.util.Scanner;

public class Demo2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your NIC: ");
        String input = scanner.nextLine().strip();

        if(input.length() != 10){
            System.out.println("Invalid Nic");
            return;
        }
        String firstNineChars = input.substring(0,9);
        if(isNumber(firstNineChars) && (input.endsWith("v") || input.endsWith("V"))){
            System.out.println("Valid NIC");
        }else{
            System.out.println("Invalid NIC");
        }
    }
    public static boolean isNumber(String input){
//        for (char c : input.toCharArray()) {
//            if(!Character.isDigit(c)) return false;
//        }
//       return true;
        try{
            Integer.parseInt(input);
            return true;
        } catch (RuntimeException t){
            t.printStackTrace();
            return false;
        }
    }
}
