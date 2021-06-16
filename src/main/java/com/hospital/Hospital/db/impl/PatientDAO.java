package com.hospital.Hospital.db.impl;

import com.hospital.Hospital.db.ConnectionPool;
import com.hospital.Hospital.db.interfaces.PatientManager;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.model.PatientAssignment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of PatientManager interface.
 */

public class PatientDAO implements PatientManager {

    private static final Logger LOG = LogManager.getLogger(PatientDAO.class);

    private static final String SELECT_ALL_PATIENTS_ORDER_BY = "SELECT * FROM patient WHERE isDischarged = false ORDER BY %s";
    private static final String SELECT_ALL_PATIENTS = "SELECT * FROM patient WHERE isDischarged = false";
    private static final String SELECT_PATIENTS_BY_DOCTOR_ID = "SELECT p.id, p.firstName, p.lastName, p.diagnosis, p.birthDate FROM patient_has_doctor pd" +
            "            JOIN doctor d ON pd.doctor_id = d.id" +
            "            JOIN patient p ON pd.patient_id = p.id" +
            "            WHERE d.id = ? AND p.isDischarged = false;";
    private static final String SELECT_PATIENTS_BY_DOCTOR_ID_ORDER_BY = "SELECT p.id, p.firstName, p.lastName, p.diagnosis, p.birthDate FROM patient_has_doctor pd\n" +
            "            JOIN doctor d ON pd.doctor_id = d.id\n" +
            "            JOIN patient p ON pd.patient_id = p.id\n" +
            "            WHERE d.id = ? AND p.isDischarged = false " +
            "            ORDER BY %s";
    private static final String SELECT_PATIENT_BY_ID = "SELECT * FROM patient WHERE id = ?";

    private static final String UPDATE_PATIENT = "UPDATE patient "
            + "SET firstName = ?, lastName = ?, diagnosis = ?,birthDate = ? "
            + "WHERE id = ?;";
    private static final String UPDATE_TREATMENT = "UPDATE patient_has_doctor " +
            "SET treatment = ? " +
            "WHERE doctor_id = ? AND patient_id = ?";
    private static final String UPDATE_DISCHARGE = "UPDATE patient " +
            "SET isDischarged = true " +
            "WHERE id = ?";

    @Override
    public List<Patient> findPatientsForDoctor(int doctorId) {

        ResultSet resultSet = null;
        List<Patient> patients = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_PATIENTS_BY_DOCTOR_ID)) {
            ps.setInt(1, doctorId);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                patients.add(getPatient(resultSet));
            }
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            LOG.error("Can't find patients for doctor");
            ConnectionPool.rollback(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
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
                patients.add(getPatient(resultSet));
            }
            ConnectionPool.commit(connection);
        } catch (Exception e) {
            LOG.error("Can't find sorted patients for doctor");
            ConnectionPool.rollback(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
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
                patients.add(getPatient(resultSet));
            }
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            LOG.error("Can't find all patients");
            ConnectionPool.rollback(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
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
                patient = getPatient(resultSet);
            }
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            LOG.error("Can't get patient by id");
            ConnectionPool.rollback(connection);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
        }
        return patient;
    }

    @Override
    public boolean dischargePatient(int patientId) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DISCHARGE)) {
            preparedStatement.setInt(1, patientId);
            preparedStatement.executeUpdate();
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            LOG.error("Can't discharge");
            ConnectionPool.rollback(connection);
            return false;
        } finally {
            ConnectionPool.close(connection);
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
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            LOG.error("Can't update patient");
            ConnectionPool.rollback(connection);
            return false;
        } finally {
            ConnectionPool.close(connection);
        }
        return true;
    }

    @Override
    public boolean updateTreatment(PatientAssignment phs) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TREATMENT)) {
            preparedStatement.setString(1, phs.getTreatment());
            preparedStatement.setInt(2, phs.getDoctorId());
            preparedStatement.setInt(3, phs.getPatientId());
            preparedStatement.executeUpdate();
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            LOG.error("Can't update treatment");
            ConnectionPool.rollback(connection);
            return false;
        } finally {
            ConnectionPool.close(connection);
        }
        return true;
    }

    public List<Patient> getSortedPatients(String columnName) {
        ResultSet resultSet = null;
        List<Patient> patients = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = connection.prepareStatement(String.format(SELECT_ALL_PATIENTS_ORDER_BY, columnName))) {
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                patients.add(getPatient(resultSet));
            }
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            LOG.error("Can't get sorted patients");
            ConnectionPool.rollback(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
        }
        return patients;
    }

    private Patient getPatient(ResultSet resultSet) throws SQLException {
        return new Patient.PatientBuilder(resultSet.getString(2), resultSet.getString(3))
                .diagnosis(resultSet.getString(4))
                .birthDate(resultSet.getDate(5).toLocalDate())
                .id(resultSet.getInt(1))
                .build();
    }

}
