package lk.ijse.dep10.collectins;

public class HashCode {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();


        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());

    }
}
class A{
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
