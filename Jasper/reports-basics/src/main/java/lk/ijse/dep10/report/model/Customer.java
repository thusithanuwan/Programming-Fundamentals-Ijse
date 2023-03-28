package lk.ijse.dep10.report.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor

public class Customer implements Serializable {
    private int id;
    private String name;
    private String address;

}
