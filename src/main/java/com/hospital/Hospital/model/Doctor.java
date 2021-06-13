package com.hospital.Hospital.model;
public class Doctor {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String position;
    private final int loginId;

    private Doctor(DoctorBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.position = builder.position;
        this.loginId = builder.loginId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public String getPosition() { return position; }

    public int getLoginId() { return loginId; }


    @Override
    public String toString() {
        return id+firstName+lastName+position+loginId;
    }

    public static class DoctorBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private String position;
        private int loginId;

        public DoctorBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public DoctorBuilder position(String position) {
            this.position = position;
            return this;
        }
        public DoctorBuilder id(int id) {
            this.id = id;
            return this;
        }

        public DoctorBuilder loginId(int loginId) {
            this.loginId = loginId;
            return this;
        }

        public Doctor build() {
            return new Doctor(this);
        }
    }

}
