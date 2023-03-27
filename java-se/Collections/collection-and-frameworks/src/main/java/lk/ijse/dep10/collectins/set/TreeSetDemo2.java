package lk.ijse.dep10.collectins.set;

import java.util.TreeSet;

public class TreeSetDemo2 {
    public static void main(String[] args) {
        TreeSet<Customer> customer = new TreeSet<>();
        customer.add(new Customer(3,"Ruwan"));
        customer.add(new Customer(1,"Nuwan"));
        customer.add(new Customer(2,"Kasun"));


        System.out.println(customer);


    }
}
class Customer implements Comparable<Customer> {
    int id;
    String name;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Customer o) {
        if(id ==o.id) return 0;
        if (id > o.id) return 1;
        return -1;
    }
}
