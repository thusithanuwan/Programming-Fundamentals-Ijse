import java.io.*;

public class BuffredReaderStreamDemo {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            viaBuffers(i + 1);
            withoutBuffer(i + 1);

        }

    }

    private static void viaBuffers(int attempt) throws IOException {

        File file = new File("bufferedfile.dep10");
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);

        String line;
        String something = "";

        long l1 = System.nanoTime();
        while ( (line = br.readLine()) != null){
            something += line;
        }
        fileReader.close();
        long l2 = System.nanoTime();

        System.out.println(String.format("B   attempt-%d: %d", attempt, (l2 - l1)));

    }

    private static void withoutBuffer(int attempt) throws IOException {

        File file = new File("bufferedfile.dep10");
        FileReader fileReader = new FileReader(file);

        char[] buffer = null;
        int read = 0;
        String something = "";

        long l1 = System.nanoTime();
        while (true){
            buffer = new char[5];
            read = fileReader.read(buffer);
            if (read == -1) {
                break;
            }
            something += new String(buffer,0,read);
        }
        fileReader.close();
        long l2 = System.nanoTime();

        System.out.println(String.format("W:B attempt-%d: %d", attempt, (l2 - l1)));




    }


}