package lk.ijse.dep10.app.model;

import java.io.Serializable;
import java.sql.Blob;

public class Student implements Serializable {
    private int id;
    private String name;
    private String address;
    private Blob picture;

    public Student() {
    }

    public Student(int id, String name, String address, Blob picture) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }
}
