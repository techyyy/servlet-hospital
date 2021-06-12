package com.hospital.Hospital.db.impl;

import com.hospital.Hospital.db.ConnectionPool;
import com.hospital.Hospital.db.interfaces.UserManager;
import com.hospital.Hospital.model.*;
import com.hospital.Hospital.model.user.Role;
import com.hospital.Hospital.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static com.hospital.Hospital.db.Queries.*;

public class UserDAO implements UserManager {
    @Override
    public User getUserByLogin(String username) {
        User user = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Role.valueOf(resultSet.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return user;
    }

    @Override
    public Doctor getDoctorByLoginId(int loginId) {
        Doctor doctor = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_USER_ID)) {
            preparedStatement.setString(1, Integer.toString(loginId));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                doctor = new Doctor(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return doctor;
    }

    @Override
    public List<Doctor> findAllDoctors() {
        ResultSet resultSet = null;
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SELECT_ALL_DOCTORS);
            while (resultSet.next()) {
                Doctor doctor = new Doctor(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return doctors;
    }

    @Override
    public Doctor getDoctorById(int id) {
        Doctor doctor = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                doctor = new Doctor(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return doctor;
    }

    @Override
    public boolean insertDoctor(Doctor doctor, User user) {
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement psLogin = connection.prepareStatement(INSERT_INTO_USER_TABLE, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psDoctor = connection.prepareStatement(INSERT_INTO_DOCTOR_TABLE)){
            psLogin.setString(1, user.getUsername());
            psLogin.setString(2, user.getPassword());
            psLogin.setString(3, user.getRole().toString());
            psLogin.executeUpdate();
            resultSet = psLogin.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
            psDoctor.setString(1, doctor.getFirstName());
            psDoctor.setString(2, doctor.getLastName());
            psDoctor.setString(3, doctor.getPosition());
            psDoctor.setInt(4, user.getId());
            psDoctor.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return false;
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return true;
    }

    @Override
    public boolean insertNurse(Nurse nurse, User user) {
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement psLogin = connection.prepareStatement(INSERT_INTO_USER_TABLE, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psDoctor = connection.prepareStatement(INSERT_INTO_NURSE_TABLE)){
            psLogin.setString(1, user.getUsername());
            psLogin.setString(2, user.getPassword());
            psLogin.setString(3, user.getRole().toString());
            psLogin.executeUpdate();

            resultSet = psLogin.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
            psDoctor.setString(1, nurse.getFirstName());
            psDoctor.setString(2, nurse.getLastName());
            psDoctor.setInt(3, user.getId());
            psDoctor.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return false;
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return true;
    }

    @Override
    public boolean insertPatient(Patient patient) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement psPatient = connection.prepareStatement(INSERT_INTO_PATIENT_TABLE)){
            psPatient.setString(1, patient.getFirstName());
            psPatient.setString(2, patient.getLastName());
            psPatient.setString(3, patient.getDiagnosis());
            psPatient.setDate(4, patient.getBirthDate());
            psPatient.setBoolean(5, false);
            psPatient.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return false;
        } finally {
            ConnectionPool.commitAndClose(connection);
        }
        return true;
    }

    @Override
    public boolean insertPatientHasDoctor(PatientHasDoctor phd) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = connection.prepareStatement(INSERT_INTO_PATIENT_HAS_DOCTOR_TABLE)){
            ps.setInt(1, phd.getDoctorId());
            ps.setInt(2, phd.getPatientId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return false;
        } finally {
            ConnectionPool.commitAndClose(connection);
        }
        return true;
    }

    @Override
    public List<DiseaseHistory> getHospitalCard(int patientId) {
        ResultSet resultSet = null;
        List<DiseaseHistory> hospitalCard = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_PATIENT_CARD)) {
            ps.setInt(1,patientId);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                DiseaseHistory dh = new DiseaseHistory(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
                hospitalCard.add(dh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return hospitalCard;
    }

    @Override
    public List<Doctor> getSortedDoctors(String columnName) {
        ResultSet resultSet = null;
        List<Doctor> doctors = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = connection.prepareStatement(String.format(SELECT_ALL_DOCTORS_ORDER_BY, columnName))) {
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Doctor doctor = new Doctor(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return doctors;
    }

    @Override
    public List<Patient> getSortedPatients(String columnName) {
        ResultSet resultSet = null;
        List<Patient> patients = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = connection.prepareStatement(String.format(SELECT_ALL_PATIENTS_ORDER_BY, columnName))) {
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return patients;
    }

}
