package lk.ijse.dep10.assignment.model;

import lk.ijse.dep10.assignment.model.util.Gender;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private Gender gender;
    private LocalDate dateOfBirth;

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String address, Gender gender, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
