package util;

public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Kasun")
                .append("Hi")
                .append("Hello")
                .insert(5," ")
                .insert(8," ")
                .append(".");
        System.out.println(stringBuilder.toString());
    }
}
