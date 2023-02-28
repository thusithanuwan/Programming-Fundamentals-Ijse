package lk.ijse.dep10.serialization.model.inherit1;

public class A {
    public int a =10;

    public A(int a) {
        this.a = a;
    }

    public A() {
        System.out.println("A's no args constructor");
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                '}';
    }
}
