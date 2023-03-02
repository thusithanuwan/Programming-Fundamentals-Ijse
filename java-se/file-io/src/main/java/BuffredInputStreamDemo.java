import java.io.*;

public class BuffredInputStreamDemo {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            viaBuffers(i + 1);
            withoutBuffer(i + 1);

        }

    }

    private static void viaBuffers(int attempt) throws IOException {

        File file = new File("bufferedfile.dep10");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        long l1 = System.nanoTime();
        byte[] bytes = bis.readAllBytes();
        bis.close();
        long l2 = System.nanoTime();

        System.out.println(String.format("Buffered attempt-%d , %d", attempt, (l2 - l1)));

    }

    private static void withoutBuffer(int attempt) throws IOException {
        File file = new File("bufferedfile.dep10");
        FileInputStream fis = new FileInputStream(file);

        long l1 = System.nanoTime();

        byte[] bytes = fis.readAllBytes();
        fis.close();
        long l2 = System.nanoTime();

        System.out.println(String.format("Without Buffered attempt-%d , %d", attempt, (l2 - l1)));




    }


}