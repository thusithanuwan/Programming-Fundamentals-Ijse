package lk.ijse.dep10.app;

import
            com.sun.jdi.ClassNotLoadedException;

import java.io.FileNotFoundException;

public class Demo1 {
    public static void main(String[] args) throws ClassNotFoundException {

        method();
        System.out.println("End of the main method");
    }
    static void method(){
        try{
            methode2();
            System.out.println("After method 2 inside the try");
        } catch(ArithmeticException t){

        }
        System.out.println("after executing methode 1");
    }
    static void methode2()  {
        try{
            System.out.println("Start trying");
            System.out.println(10/0);
            System.out.println("End trying");
        } catch (AssertionError t){  // If we said to catch arithmetic exception it catches here
            System.out.println(t);
        }
        System.out.println("Ending of the methode 2");
    }
}
