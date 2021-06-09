package com.hospital.Hospital.model;

public class Nurse {
    private int id;
    private String firstName;
    private String lastName;
    private int loginId;

    public Nurse(int id, String firstName, String lastName, int loginId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginId = loginId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }
}
