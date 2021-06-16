package com.hospital.Hospital.db.interfaces;

import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.model.PatientAssignment;

import java.util.List;

public interface PatientManager {
    /**
     * Get doctor's patients
     *
     * @param doctorId - doctor's id
     *
     * @return list of doctor's patients.
     */
    List<Patient> findPatientsForDoctor(int doctorId);

    /**
     * Get sorted doctor's patients
     *
     * @param doctorId - doctor's id
     * @param columnName - column to sort by
     *
     * @return list of doctor's patients.
     */
    List<Patient> findSortedPatientsForDoctor(int doctorId, String columnName);

    /**
     * Get all patients
     *
     * @return list of all patients in the hospital.
     */
    List<Patient> findAllPatients();

    /**
     * Get all patients sorted by
     * @param columnName - column to sort by
     *
     * @return list of all patients sorted.
     */
    List<Patient> getSortedPatients(String columnName);

    /**
     * Get patient by id
     *
     * @param id - id to search for
     *
     * @return Patient object if found, null otherwise.
     */
    Patient getPatientById(int id);

    /**
     * Discharge patient
     *
     * @return true if operation succeeded.
     */
    boolean dischargePatient(int patientId);

    /**
     * Update patient
     *
     * @return true if operation succeeded.
     */
    boolean updatePatient(Patient patient);

    /**
     * Update treatment
     *
     * @return true if operation succeeded.
     */
    boolean updateTreatment(PatientAssignment phs);
}
