package com.hospital.Hospital.model;

public class DiseaseHistory {
    private final String patientFirstName;
    private final String patientLastName;
    private final String diagnosis;
    private final String doctorFirstName;
    private final String doctorLastName;
    private final String doctorPosition;
    private final String treatment;

    public DiseaseHistory(String patientFirstName, String patientLastName, String diagnosis,
                          String doctorFirstName, String doctorLastName, String doctorPosition,
                          String treatment) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.diagnosis = diagnosis;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.doctorPosition = doctorPosition;
        this.treatment = treatment;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }


    public String getPatientLastName() {
        return patientLastName;
    }


    public String getDiagnosis() {
        return diagnosis;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public String getDoctorPosition() {
        return doctorPosition;
    }

    public String getTreatment() {
        return treatment;
    }

    @Override
    public String toString() {
        return diagnosis+doctorFirstName+doctorLastName+doctorPosition+treatment;
    }
}
