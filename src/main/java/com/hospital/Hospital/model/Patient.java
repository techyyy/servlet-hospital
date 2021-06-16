package com.hospital.Hospital.model;

import java.time.LocalDate;


/**
 *
 * Patient entity
 *
 */
public class Patient {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String diagnosis;
    private final LocalDate birthDate;

    private Patient(PatientBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.diagnosis = builder.diagnosis;
        this.birthDate = builder.birthDate;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public final String toString() {
        return firstName+lastName+diagnosis+birthDate;
    }

    public static class PatientBuilder {
        private int id;
        private final String firstName;
        private final String lastName;
        private String diagnosis;
        private LocalDate birthDate;

        public PatientBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public PatientBuilder diagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
            return this;
        }
        public PatientBuilder id(int id) {
            this.id = id;
            return this;
        }
        public PatientBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Patient build() {
            return new Patient(this);
        }
    }
}

