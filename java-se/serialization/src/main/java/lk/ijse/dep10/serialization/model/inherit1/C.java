package lk.ijse.dep10.serialization.model.inherit1;

import java.io.Serializable;

public class C extends B implements Serializable {
    public int C = 30;

    public C(int a, int b, int c) {
        super(a, b);
        C = c;
    }

    public C() {
        System.out.println("C's no args constructor");

    }

    @Override
    public String toString() {
        return "C{" +
                "C=" + C +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
