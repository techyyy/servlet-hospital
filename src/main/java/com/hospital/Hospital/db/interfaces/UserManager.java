package com.hospital.Hospital.db.interfaces;

import com.hospital.Hospital.model.*;
import com.hospital.Hospital.model.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserManager {

    /**
     * Get user by username
     *
     * @param username - username to search for
     *
     * @return User object if found, null otherwise.
     */
    User getUserByLogin(String username);

    /**
     * Get doctor by login id
     *
     * @param loginId - id to search for
     *
     * @return Doctor object if found, null otherwise.
     */
    Doctor getDoctorByLoginId(int loginId);

    /**
     * Get all doctors
     *
     * @return list of all doctors.
     */
    List<Doctor> findAllDoctors();

    /**
     * Get doctor by id
     *
     * @param id - id to search for
     *
     * @return Doctor object if found, null otherwise.
     */
    Doctor getDoctorById(int id);

    /**
     * Register a doctor
     *
     * @param doctor
     * @param user
     *
     * @return true if operation succeeded.
     */
    boolean insertDoctor(Doctor doctor, User user);

    /**
     * Register a nurse
     *
     * @param nurse
     * @param user
     *
     * @return true if operation succeeded.
     */
    boolean insertNurse(Nurse nurse, User user);

    /**
     * Register a patient
     *
     * @param patient
     *
     * @return true if operation succeeded.
     */
    boolean insertPatient(Patient patient);

    /**
     * Add patient assignment
     *
     * @param phd - PatientAssignment object
     *
     * @return true if operation succeeded.
     *
     * @throws SQLException if there's no such patient/doctor id
     */
    boolean insertPatientAssignment(PatientAssignment phd) throws SQLException;

    /**
     * Getting hospital card for patient
     *
     * @param patientId - patient's id
     *
     * @return DiseaseHistory list
     */
    List<DiseaseHistory> getHospitalCard(int patientId);

    /**
     * All doctors sorted
     *
     * @param columnName - column to sort by
     *
     * @return list of sorted doctors.
     */
    List<Doctor> getSortedDoctors(String columnName);
}
