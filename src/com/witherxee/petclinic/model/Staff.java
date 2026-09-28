package com.witherxee.petclinic.model;

public class Staff {

    private int staffId;
    private String username;
    private String password;
    private String name;
    private String role;

    public Staff() {
    }

    public Staff(int staffId, String username, String password, String name, String role) {
        this.staffId = staffId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public int getStaffId() {
        return staffId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}