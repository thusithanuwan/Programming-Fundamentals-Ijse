package lk.ijse.dep10.serialization.model.inherit1;

import java.io.Serializable;

public class D extends C  {

    private int d = 10;

    public D(int a, int b, int c, int d) {
        super(a, b, c);
        this.d = d;
    }
    public D() {
        System.out.println("D's no args constructor");

    }

    @Override
    public String toString() {
        return "D{" +
                "d=" + d +
                ", C=" + C +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
