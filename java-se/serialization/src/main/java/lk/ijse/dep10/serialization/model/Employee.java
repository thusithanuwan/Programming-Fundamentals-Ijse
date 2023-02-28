package lk.ijse.dep10.serialization.model;

import javafx.scene.control.Button;

import java.awt.*;
import java.io.Serializable;

public class Employee implements Serializable {
    private String id;
    private PersonInfo employeeInfo;
    private Status status;
    private PersonInfo spouseInfo;

    private transient javafx.scene.control.Button btnRemove;

    public Employee() {
    }

    public Employee(String id, PersonInfo employeeInfo, Status status, PersonInfo spouseInfo, javafx.scene.control.Button btnRemove) {
        this.id = id;
        this.employeeInfo = employeeInfo;
        this.status = status;
        this.spouseInfo = spouseInfo;
        this.btnRemove = btnRemove;
    }

    public String getEmployeeName() {
        return employeeInfo.getName();
    }

    public String getSpouseName() {
        return this.status == Status.MARRIED ? spouseInfo.getName() : "-";
    }

    public String getId() {
        return id;
    }

    public Button getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(javafx.scene.control.Button btnRemove) {
        this.btnRemove = btnRemove;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(PersonInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PersonInfo getSpouseInfo() {
        return spouseInfo;
    }

    public void setSpouseInfo(PersonInfo spouseInfo) {
        this.spouseInfo = spouseInfo;
    }
}
