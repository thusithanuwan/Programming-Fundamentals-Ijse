import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class CompareDemo {
    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal(100);
        BigDecimal bigDecimal2 = new BigDecimal(59);
        BigDecimal bigDecimal3 = new BigDecimal(98);
        BigDecimal bigDecimal4 = new BigDecimal(45);

        System.out.println(bigDecimal1.compareTo(bigDecimal2));
        System.out.println(bigDecimal4.compareTo(bigDecimal1));

        System.out.println(bigDecimal1.compareTo(bigDecimal1));

        System.out.println(bigDecimal1.equals(bigDecimal1));

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");


        Student kasun = new Student(1, "Kasun", 50);
        Student nuwan = new Student(2, "Nuwan", 100);
        Student nimal = new Student(3, "Nimal", 50);

        System.out.println(kasun.compareTo(nuwan));
        System.out.println(kasun.compareTo(nimal));


        ArrayList<Student> students = new ArrayList<>();
        students.add(kasun);
        students.add(nuwan);
        students.add(nimal);
        System.out.println(students);




    }


}
class Student implements Comparable<Student> {
    int id;
    String name;
    int mark;

    public Student(int id, String name, int mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    @Override
    public int compareTo(Student o) {
        if (mark == o.mark) return 0;
        if (mark > o.mark) return 1;
        return -1;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
