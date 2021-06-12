package com.hospital.Hospital.model;

import java.time.LocalDate;

public class Patient {

    private int id;
    private String firstName;
    private String lastName;
    private String diagnosis;
    private LocalDate birthDate;
    private boolean isDischarged;

    public Patient() {
    }

    public Patient(int id, String firstName, String lastName, String diagnosis, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.diagnosis = diagnosis;
        this.birthDate = birthDate;
    }

    public Patient(int id, String firstName, String lastName, String diagnosis, LocalDate birthDate, boolean isDischarged) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.diagnosis = diagnosis;
        this.birthDate = birthDate;
        this.isDischarged = isDischarged;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public final String toString() {
        return firstName+lastName+diagnosis+birthDate;
    }
}
