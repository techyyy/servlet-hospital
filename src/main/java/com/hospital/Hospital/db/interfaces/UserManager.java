package com.hospital.Hospital.db.interfaces;

import com.hospital.Hospital.model.*;
import com.hospital.Hospital.model.user.User;

import java.util.List;

public interface UserManager {
    User getUserByLogin(String username);
    Doctor getDoctorByLoginId(int loginId);
    List<Doctor> findAllDoctors();
    Doctor getDoctorById(int id);
    boolean insertDoctor(Doctor doctor, User user);
    boolean insertNurse(Nurse nurse, User user);
    boolean insertPatient(Patient patient);
    boolean insertPatientHasDoctor(PatientHasDoctor phd);
    List<DiseaseHistory> getHospitalCard(int patientId);
    List<Doctor> getSortedDoctors(String columnName);
}
