import java.io.*;

public class BufferdOutPutStreamDemo {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            viaBuffers(i+1);
            withoutBuffer(i+1);

        }

    }

    private static void viaBuffers(int attempt) throws IOException {

        File file = new File("bufferedfile.dep10");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        long l1 = System.nanoTime();
        bos.write("I want to Write".getBytes());
        long l2 = System.nanoTime();

        System.out.println(String.format("Buffered attempt-%d , %d",attempt,(l2-l1)));

        bos.close();
//        System.out.println("Saved!");
    }

    private static void withoutBuffer(int attempt) throws IOException {
        File file = new File("bufferedfile.dep10");
        FileOutputStream fos = new FileOutputStream(file);

        long l1 = System.nanoTime();
        fos.write("I want to Write".getBytes());
        fos.close();
        long l2 = System.nanoTime();

        System.out.println(String.format("Without Buffered attempt-%d , %d",attempt,(l2-l1)));


//        System.out.println("Saved!");


    }


}
