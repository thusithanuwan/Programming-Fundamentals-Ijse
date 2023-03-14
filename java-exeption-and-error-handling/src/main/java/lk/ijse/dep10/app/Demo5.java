package lk.ijse.dep10.app;

import java.util.Scanner;

public class Demo5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a number:");
            int result = getResult(scanner);
            System.out.println("Result is :" + result);

        }
    }

    private static int getResult(Scanner scanner){
        try {
            int x = scanner.nextInt();
            int result = 100/x;
            return result;

        } catch (ArithmeticException e) {
            System.out.println("Something went wrong.");
            return -1;
        } finally {
            System.out.println("Finally");
        }

    }
}
