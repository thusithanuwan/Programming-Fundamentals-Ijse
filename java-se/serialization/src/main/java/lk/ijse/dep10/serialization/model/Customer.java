package lk.ijse.dep10.serialization.model;

import java.io.Serializable;

public class Customer implements Serializable {
    String id;
    String name;
    transient String address;
    private static final long serialVersionUID = -5410489414194633696L;

    public Customer() {
    }

    public Customer(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
