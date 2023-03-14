package lk.ijse.dep10.app;

public class Demo4 {

    public static void main(String[] args) throws Exception {

        MyResource myResource3 = new MyResource();

        try (MyResource myResource = new MyResource(); MyResource1 myResource1 = new MyResource1(); myResource3) {
            System.out.println(myResource);
            System.out.println(myResource1);
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
class MyResource1 implements AutoCloseable {

    {
        System.out.println("Resource1");
    }

    @Override
    public void close() throws Exception {

    }
    class MyResource3 implements AutoCloseable {

        {
            System.out.println("Resource1");
        }

        @Override
        public void close() throws Exception {
            System.out.println("Resources1 is about to free.");
        }
    }
}