package lk.ijse.dep10.collectins;

import java.util.HashSet;
import java.util.Objects;
import java.util.TreeSet;

public class HashSetDemo5 {
    public static void main(String[] args) {
        HashSet<Student> student = new HashSet<>();
        student.add(new Student(3, "Ruwan"));
        student.add(new Student(1, "Nuwan"));
        student.add(new Student(1, "Nuwan"));
        student.add(new Student(2, "Kasun"));

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
        return 4;
//        return Objects.hash(id,name);
    }
    @Override
    public boolean equals(Object obj) {
        Student s = (Student) obj;
        return id==s.id && name.equals(s.name) ;
    }
}
