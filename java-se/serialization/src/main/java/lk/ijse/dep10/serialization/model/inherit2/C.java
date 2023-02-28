package lk.ijse.dep10.serialization.model.inherit2;

public class C extends B {
    public int c = 30;

    public C(){
        System.out.println("C's No arg constructor");
    }


    public C(int a, int b, int c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public String toString() {
        return "C{" +
                "c=" + c +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
