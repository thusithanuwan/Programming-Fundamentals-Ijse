package lk.ijse.dep10.serialization.model.inherit1;

public class B extends A{
    public int b = 20;

    public B(int a, int b) {
        super(a);
        this.b = b;
    }

    public B() {
        System.out.println("B's no args constructor");
    }

    @Override
    public String toString() {
        return "B{" +
                "b=" + b +
                ", a=" + a +
                '}';
    }
}
