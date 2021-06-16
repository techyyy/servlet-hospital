package com.hospital.Hospital.model;

/**
 *
 * Nurse entity
 *
 */
public class Nurse {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final int loginId;

    private Nurse(NurseBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.loginId = builder.loginId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() { return lastName; }

    public int getLoginId() { return loginId; }


    @Override
    public String toString() {
        return id+firstName+lastName+loginId;
    }

    public static class NurseBuilder {
        private int id;
        private final String firstName;
        private final String lastName;
        private int loginId;

        public NurseBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public NurseBuilder id(int id) {
            this.id = id;
            return this;
        }

        public NurseBuilder loginId(int loginId) {
            this.loginId = loginId;
            return this;
        }

        public Nurse build() {
            return new Nurse(this);
        }
    }
}
