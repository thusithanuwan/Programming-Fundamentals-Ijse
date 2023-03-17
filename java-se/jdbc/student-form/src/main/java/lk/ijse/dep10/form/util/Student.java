package lk.ijse.dep10.form.util;

import java.sql.Date;

public class Student {
    private int id;
    private String firstname;
    private String lastname;
    private String address;
    private Gender gender;
    private String date;

    public Student() {
    }

    public Student(int id, String firstname, String lastname, String address, Gender gender, String date) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.gender = gender;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDate() {
        return date;
    }

}
