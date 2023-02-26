package lk.ijse.dep10.serialization.model;

import java.io.Serializable;

public class Student implements Serializable {
    public String id;
    public String name;
    public String address;

    public Student(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }


}
