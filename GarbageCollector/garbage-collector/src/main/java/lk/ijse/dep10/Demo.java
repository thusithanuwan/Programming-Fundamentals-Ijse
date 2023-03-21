package lk.ijse.dep10;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("All object are about to destroy now");
        }));


        A a = new A();
        B b = new B();
        C.a = a;
        b.a =a;
        a = null;
        b = null;
        System.gc();
        System.out.println("Before Exit");
        Thread.sleep(2000);

    }
}

class A {
    @Override
    protected void finalize() throws Throwable {
        System.out.println(this + " A is about to destroy");
    }
}

class B {
    A a;

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this + " B is about to destroy");
    }
}

class C {
    static A a;

}