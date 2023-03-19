package lk.ijse.dep10.app;public class Demo3 {
    public static void main(String[] args) {
//        try {
            myMethod1();
//        } catch (ClassNotFoundException e) {
//
//        }
    }

    private static void myMethod1()  {
        System.out.println("Enter meMethod1");
        throw new ArithmeticException();
    }
}
