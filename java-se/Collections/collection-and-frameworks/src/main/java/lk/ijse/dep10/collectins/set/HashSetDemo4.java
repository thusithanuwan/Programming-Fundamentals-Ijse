package lk.ijse.dep10.collectins.set;

import java.util.HashSet;
import java.util.Objects;

public class HashSetDemo4 {
    public static void main(String[] args) {
        HashSet<Student1> student = new HashSet<>();
        student.add(new Student1(3, "Ruwan"));
        student.add(new Student1(1, "Nuwan"));
        student.add(new Student1(1, "Nuwan"));
        student.add(new Student1(2, "Kasun"));

        System.out.println(student);
    }
}
class Student {
    int id;
    String name;
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    @Override
    public int hashCode() {
//        return 4;
        return Objects.hash(id,name);
    }
    @Override
    public boolean equals(Object obj) {
        Student1 s = (Student1) obj;
        return id==s.id && name.equals(s.name) ;
    }
}
