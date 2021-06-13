package com.hospital.Hospital.model;

public class PatientAssignment {
    private final int doctorId;
    private final int patientId;
    private final String treatment;

    private PatientAssignment(PatientAssignmentBuilder builder) {
        this.doctorId = builder.doctorId;
        this.patientId = builder.patientId;
        this.treatment = builder.treatment;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getTreatment() {
        return treatment;
    }

    public static class PatientAssignmentBuilder {
        private final int doctorId;
        private final int patientId;
        private String treatment;

        public PatientAssignmentBuilder(int doctorId, int patientId) {
            this.doctorId = doctorId;
            this.patientId = patientId;
        }
        public PatientAssignmentBuilder diagnosis(String treatment) {
            this.treatment = treatment;
            return this;
        }

        public PatientAssignment build() {
            return new PatientAssignment(this);
        }
    }

}
