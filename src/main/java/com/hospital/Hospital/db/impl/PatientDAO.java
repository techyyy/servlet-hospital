package com.hospital.Hospital.db.impl;

import com.hospital.Hospital.db.ConnectionPool;
import com.hospital.Hospital.db.interfaces.PatientManager;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.model.PatientHasDoctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.hospital.Hospital.db.Queries.*;

public class PatientDAO implements PatientManager {

    @Override
    public List<Patient> findPatientsForDoctor(int doctorId) {
        ResultSet resultSet = null;
        List<Patient> patients = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_PATIENTS_BY_DOCTOR_ID)) {
            ps.setInt(1, doctorId);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate());
                patients.add(patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return patients;
    }

    @Override
    public List<Patient> findSortedPatientsForDoctor(int doctorId, String columnName) {
        ResultSet resultSet = null;
        List<Patient> patients = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = connection.prepareStatement(String.format(SELECT_PATIENTS_BY_DOCTOR_ID_ORDER_BY, columnName))) {
            ps.setInt(1, doctorId);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate());
                patients.add(patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return patients;
    }

    @Override
    public List<Patient> findAllPatients() {
        ResultSet resultSet = null;
        List<Patient> patients = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SELECT_ALL_PATIENTS);
            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate());
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

    @Override
    public Patient getPatientById(int id) {
        Patient patient = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                patient = new Patient(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.commitAndClose(connection);
        }
        return patient;
    }

    @Override
    public boolean dischargePatient(int patientId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DISCHARGE)) {
            preparedStatement.setInt(1, patientId);
            preparedStatement.executeUpdate();
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
    public boolean updatePatient(Patient patient) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT)) {
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3, patient.getDiagnosis());
            preparedStatement.setDate(4, Date.valueOf(patient.getBirthDate()));
            preparedStatement.setInt(5, patient.getId());
            preparedStatement.executeUpdate();
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
    public boolean updateTreatment(PatientHasDoctor phs) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TREATMENT)) {
            preparedStatement.setString(1, phs.getTreatment());
            preparedStatement.setInt(2, phs.getDoctorId());
            preparedStatement.setInt(3, phs.getPatientId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollbackAndClose(connection);
            return false;
        } finally {
            ConnectionPool.commitAndClose(connection);
        }
        return true;
    }
}
