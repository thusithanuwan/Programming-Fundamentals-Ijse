package lk.ijse.dep10;

public class Demo {
    @MyAnnotation(value = "" , name = "", getNumber = 5)
    public static void main(String[] args) {


        System.out.println("Hello Lombok!");
        Student s1 = new Student(1, "Kasun", "077-0989098");
        Student s2 = new Student(1, "Kasun", "077-0989098");
        System.out.println(s1);
        System.out.println(s2);

        System.out.println("s1=s2 : " +(s1==s2) ); //false
        System.out.println("s1 equals s2 :" + (s1.equals(s2))); //
        System.out.println("s1.hashcode() == s2.hashcode() :" + (s1.hashCode() == s2.hashCode()));
        System.out.println("__________________________________________________________________________________________");
        System.out.printf("id=%s,name=%s,contact=%s \n",s1.getId(),s1.getName(),s1.getContact());
        s2.setId(2);
        s2.setName("Nuwan");
        s2.setContact("037-9900899");
        System.out.printf("id=%s,name=%s,contact=%s \n",s2.getId(),s2.getName(),s2.getContact());

    }
}
