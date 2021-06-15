package com.hospital.Hospital.db.impl;

import com.hospital.Hospital.db.ConnectionPool;
import com.hospital.Hospital.db.interfaces.UserManager;
import com.hospital.Hospital.model.*;
import com.hospital.Hospital.model.user.Role;
import com.hospital.Hospital.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDAO implements UserManager {

    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM login WHERE username = ?";
    private static final String SELECT_DOCTOR_BY_USER_ID = "SELECT * FROM doctor WHERE login_id = ?";
    private static final String SELECT_ALL_DOCTORS = "SELECT * FROM doctor";
    private static final String SELECT_ALL_DOCTORS_ORDER_BY = "SELECT * FROM doctor ORDER BY %s";
    private static final String SELECT_DOCTOR_BY_ID = "SELECT * FROM doctor WHERE id = ?";
    private static final String SELECT_PATIENT_CARD = "SELECT patient.firstName,patient.lastName,diagnosis,doctor.firstName,doctor.lastName,position,treatment" +
            " FROM patient INNER JOIN patient_has_doctor " +
            "ON patient.id = patient_has_doctor.patient_id " +
            "INNER JOIN doctor " +
            "ON doctor.id = patient_has_doctor.doctor_id" +
            " WHERE patient.id = ?";

    private static final String INSERT_INTO_USER_TABLE = "INSERT INTO login VALUES(0, ?, ?, ?)";
    private static final String INSERT_INTO_DOCTOR_TABLE = "INSERT INTO doctor VALUES(0,?,?,?,?)";
    private static final String INSERT_INTO_NURSE_TABLE = "INSERT INTO nurse VALUES(0,?,?,?)";
    private static final String INSERT_INTO_PATIENT_TABLE = "INSERT INTO patient VALUES(0,?,?,?,?,?)";
    private static final String INSERT_INTO_PATIENT_HAS_DOCTOR_TABLE = "INSERT INTO patient_has_doctor VALUES(?,?,'')";

    @Override
    public User getUserByLogin(String username) {
        User user = null;
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user = getUser(resultSet);
            }
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollback(connection);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
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
                doctor = getDoctor(resultSet);
            }
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollback(connection);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
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
                doctors.add(getDoctor(resultSet));
            }
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollback(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
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
                doctor = getDoctor(resultSet);
            }
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollback(connection);
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
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
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            psDoctor.setString(1, doctor.getFirstName());
            psDoctor.setString(2, doctor.getLastName());
            psDoctor.setString(3, doctor.getPosition());
            psDoctor.setInt(4, id);
            psDoctor.executeUpdate();
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollback(connection);
            return false;
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
        }
        return true;
    }

    @Override
    public boolean insertNurse(Nurse nurse, User user) {
        ResultSet resultSet = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement psLogin = connection.prepareStatement(INSERT_INTO_USER_TABLE, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psNurse = connection.prepareStatement(INSERT_INTO_NURSE_TABLE)){
            psLogin.setString(1, user.getUsername());
            psLogin.setString(2, user.getPassword());
            psLogin.setString(3, user.getRole().toString());
            psLogin.executeUpdate();

            resultSet = psLogin.getGeneratedKeys();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            psNurse.setString(1, nurse.getFirstName());
            psNurse.setString(2, nurse.getLastName());
            psNurse.setInt(3, id);
            psNurse.executeUpdate();
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollback(connection);
            return false;
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
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
            psPatient.setDate(4, Date.valueOf(patient.getBirthDate()));
            psPatient.setBoolean(5, false);
            psPatient.executeUpdate();
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollback(connection);
            return false;
        } finally {
            ConnectionPool.close(connection);
        }
        return true;
    }

    @Override
    public boolean insertPatientAssignment(PatientAssignment phd) {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = connection.prepareStatement(INSERT_INTO_PATIENT_HAS_DOCTOR_TABLE)){
            ps.setInt(1, phd.getDoctorId());
            ps.setInt(2, phd.getPatientId());
            ps.executeUpdate();
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollback(connection);
            return false;
        } finally {
            ConnectionPool.close(connection);
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
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollback(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
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
                doctors.add(getDoctor(resultSet));
            }
            ConnectionPool.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            ConnectionPool.rollback(connection);
            return Collections.emptyList();
        } finally {
            ConnectionPool.close(resultSet);
            ConnectionPool.close(connection);
        }
        return doctors;
    }

    private Doctor getDoctor(ResultSet resultSet) throws SQLException {
        return new Doctor.DoctorBuilder(resultSet.getString(2), resultSet.getString(3))
                .position(resultSet.getString(4))
                .id(resultSet.getInt(1))
                .loginId(resultSet.getInt(5))
                .build();
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        return new User.UserBuilder(resultSet.getString(2),
                resultSet.getString(3),
                Role.valueOf(resultSet.getString(4)))
                .id(resultSet.getInt(1))
                .build();
    }

}
