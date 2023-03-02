package util;

public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Kasun")
                .append("Hi")
                .append("Hello")
                .insert(4," ")
                .append(".");
        System.out.println(stringBuilder.toString());
    }
}
