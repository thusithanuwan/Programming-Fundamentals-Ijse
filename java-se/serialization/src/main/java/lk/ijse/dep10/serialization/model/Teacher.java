package lk.ijse.dep10.serialization.model;

import java.io.Serializable;

public class Teacher implements Serializable {
    int id;
     static String name = "Thusitha";
    private static final long serialVersionUID = 4547235592138872336L;

    public Teacher(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
