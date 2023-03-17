package lk.ijse.dep10.assignment.model;

import java.io.Serializable;

public class User implements Serializable {
    private String fullName;
    private String username;
    private String password;
    private Role role;

    public enum Role{
        ADMIN,USER
    }


    public User() {
    }

    public User(String fullName, String username, String password,Role role) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role =role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


