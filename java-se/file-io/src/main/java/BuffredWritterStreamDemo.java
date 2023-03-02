import java.io.*;

public class BuffredWritterStreamDemo {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            viaBuffers(i + 1);
            withoutBuffer(i + 1);

        }

    }

    private static void viaBuffers(int attempt) throws IOException {

        File file = new File("bufferedfile.dep10");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter br = new BufferedWriter(fileWriter);


        long l1 = System.nanoTime();
        br.write("Hello World!");
        long l2 = System.nanoTime();

        System.out.println(String.format("B   attempt-%d: %d", attempt, (l2 - l1)));

    }

    private static void withoutBuffer(int attempt) throws IOException {

        File file = new File("bufferedfile.dep10");
        FileWriter fileWriter = new FileWriter(file);

        long l1 = System.nanoTime();
        fileWriter.write("Hello World!");
        fileWriter.close();
        long l2 = System.nanoTime();


        System.out.println(String.format("W:B attempt-%d: %d", attempt, (l2 - l1)));




    }


}