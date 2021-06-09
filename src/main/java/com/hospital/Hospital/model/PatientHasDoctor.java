package com.hospital.Hospital.model;

public class PatientHasDoctor {
    private int doctorId;
    private int patientId;
    private String treatment;

    public PatientHasDoctor(){}

    public PatientHasDoctor(int doctorId, int patientId, String treatment) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.treatment = treatment;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
