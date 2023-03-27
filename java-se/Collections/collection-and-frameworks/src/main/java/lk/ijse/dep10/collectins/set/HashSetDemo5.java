package lk.ijse.dep10.collectins.set;

import java.util.HashSet;

public class HashSetDemo5 {
    public static void main(String[] args) {
        HashSet<Student1> student = new HashSet<>();
        student.add(new Student1(3, "Ruwan"));
        student.add(new Student1(1, "Nuwan"));
        student.add(new Student1(1, "Nuwan"));
        student.add(new Student1(2, "Kasun"));

        System.out.println(student);
    }
}
class Student1 {
    int id;
    String name;
    public Student1(int id, String name) {
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
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Student1 s = (Student1) obj;
        return id==s.id && name.equals(s.name) ;
    }
}
