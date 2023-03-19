package lk.ijse.dep10.app;

public class Demo4 {

    public static void main(String[] args) throws Exception {

        MyResource2.MyResource3 myResource3 = new MyResource2.MyResource3();

        try (MyResource myResource = new MyResource(); MyResource2 myResource2 = new MyResource2(); myResource3) {
            System.out.println(myResource);
            System.out.println(myResource2);
            System.out.println(myResource3);
        }
//        myResource.close();

    }
}

class MyResource implements AutoCloseable {

    {
        System.out.println("Resource");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Resources is about to free.");
    }
}

class MyResource2 implements AutoCloseable {

    {
        System.out.println("Resource2");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Resource2 is about to free.");
    }

    static class MyResource3 implements AutoCloseable {

        {
            System.out.println("Resource3");
        }

        @Override
        public void close() throws Exception {
            System.out.println("Resources3 is about to free.");
        }
    }
}