package com.hospital.Hospital.db.interfaces;

import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.model.PatientAssignment;

import java.util.List;

public interface PatientManager {
    List<Patient> findPatientsForDoctor(int doctorId);
    List<Patient> findSortedPatientsForDoctor(int doctorId, String columnName);
    List<Patient> findAllPatients();
    List<Patient> getSortedPatients(String columnName);
    Patient getPatientById(int id);
    boolean dischargePatient(int patientId);
    boolean updatePatient(Patient patient);
    boolean updateTreatment(PatientAssignment phs);
}
