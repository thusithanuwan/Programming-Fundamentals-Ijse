package lk.ijse.dep10.collectins.util;

public class HashCode2 {
    public static void main(String[] args) {
        C c1 = new C();
        C c2 = new C();


        System.out.println(c1.hashCode() == c2.hashCode());

    }
}
class C{
    @Override
    public int hashCode() {
        return 5;
    }
}
