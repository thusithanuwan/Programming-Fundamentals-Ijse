package lk.ijse.dep10.serialization.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PersonInfo implements Serializable {
    private String name;
    private ArrayList<String> contact;

    public PersonInfo() {
    }

    public PersonInfo(String name, ArrayList<String> contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getContact() {
        return contact;
    }

    public void setContact(ArrayList<String> contact) {
        this.contact = contact;
    }
}
