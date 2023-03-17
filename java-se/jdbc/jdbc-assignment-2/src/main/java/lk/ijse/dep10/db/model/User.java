package lk.ijse.dep10.db.model;

import lk.ijse.dep10.db.model.util.Role;

import java.io.Serializable;

public class User implements Serializable {
    String fullName;
    String userName;
    String password;
    Role role;

    public User() {
    }

    public User(String fullName, String userName, String password, Role role) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
