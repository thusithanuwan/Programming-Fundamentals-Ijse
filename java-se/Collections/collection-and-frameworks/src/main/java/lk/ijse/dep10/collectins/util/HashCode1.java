package lk.ijse.dep10.collectins.util;

public class HashCode1{
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();


        System.out.println(a1.hashCode());  // by default Hashcode return memory location
        System.out.println(a2.hashCode());
        System.out.println(args.equals(a2));  // by default returns memory locations

        a1 =a2;

        System.out.println(a1.hashCode());  // by default Hashcode return memory location
        System.out.println(a2.hashCode());
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a1.equals(a2));  // by default returns memory locations




    }
}
class A{

}
