package lk.ijse.dep10.assignment.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    private int id;
    private String name;
    private String address;
    private ArrayList<String> contactList = new ArrayList<>();

    public Customer() {
    }

    public Customer(int id, String name, String address, ArrayList<String> contactList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactList = contactList;
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

    public ArrayList<String> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<String> contactList) {
        this.contactList = contactList;
    }
}
