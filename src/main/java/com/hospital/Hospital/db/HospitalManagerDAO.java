package com.hospital.Hospital.db;

import com.hospital.Hospital.model.*;
import com.hospital.Hospital.model.user.Role;
import com.hospital.Hospital.model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.hospital.Hospital.db.Queries.*;

public class HospitalManagerDAO {

    private static final Lock CONNECTION_LOCK = new ReentrantLock();
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/myhospital?user=root&password=admin";

    private static HospitalManagerDAO instance;
    private static Connection connection;

    private static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Can't close result set" + e.getMessage());
            }
        }
    }

    private HospitalManagerDAO() {
        getConnection();
    }

    public static synchronized HospitalManagerDAO getInstance(){
        if(instance == null) {
            instance = new HospitalManagerDAO();
        }
        return instance;
    }

    public static Connection getConnection() {

        /*if(connection != null) {
            return connection;
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL);
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println("Can't get connection" + e.getMessage());
            }
        }
        return connection;*/
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Patient> findPatientsForDoctor(int doctorId) {
        ResultSet resultSet = null;
        List<Patient> patients = new ArrayList<>();
        CONNECTION_LOCK.lock();
        try(PreparedStatement ps = connection.prepareStatement(SELECT_PATIENTS_BY_DOCTOR_ID)) {
            ps.setInt(1, doctorId);
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
        } catch (Exception e) {
            System.out.println("Can't find patients for doctor" + e.getMessage());
            return Collections.emptyList();
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return patients;
    }

    public List<Patient> findSortedPatientsForDoctor(int doctorId, String columnName) {
        ResultSet resultSet = null;
        List<Patient> patients = new ArrayList<>();
        CONNECTION_LOCK.lock();
        try(PreparedStatement ps = connection.prepareStatement(String.format(SELECT_PATIENTS_BY_DOCTOR_ID_ORDER_BY, columnName))) {
            ps.setInt(1, doctorId);
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
        } catch (Exception e) {
            System.out.println("Can't find patients for doctor" + e.getMessage());
            return Collections.emptyList();
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return patients;
    }


    public List<Patient> findAllPatients() {
        ResultSet resultSet = null;
        List<Patient> patients = new ArrayList<>();
        CONNECTION_LOCK.lock();
        try(Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(SELECT_ALL_PATIENTS);
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
            System.err.println("Can't find patients" + e.getMessage());
            return Collections.emptyList();
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return patients;
    }

    public User getUserByLogin(String username) {
        User user = null;
        ResultSet resultSet = null;
        CONNECTION_LOCK.lock();
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
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return user;
    }

    public Doctor getDoctorByLoginId(int loginId) {
        Doctor doctor = null;
        ResultSet resultSet = null;
        CONNECTION_LOCK.lock();
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
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return doctor;
    }

    public List<Doctor> findAllDoctors() {
        ResultSet resultSet = null;
        List<Doctor> doctors = new ArrayList<>();
        CONNECTION_LOCK.lock();
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
            System.err.println("Can't find doctors" + e.getMessage());
            return Collections.emptyList();
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return doctors;
    }

    public Patient getPatientById(int id) {
        Patient patient = null;
        ResultSet resultSet = null;
        CONNECTION_LOCK.lock();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                patient = new Patient(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return patient;
    }

    public Doctor getDoctorById(int id) {
        Doctor doctor = null;
        ResultSet resultSet = null;
        CONNECTION_LOCK.lock();
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
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return doctor;
    }

    public boolean dischargePatient(int patientId) {
        CONNECTION_LOCK.lock();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DISCHARGE)) {
            preparedStatement.setInt(1, patientId);
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Can't update patient." + e.getMessage());
            return false;
        } finally {
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public boolean updatePatient(Patient patient) {
        CONNECTION_LOCK.lock();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PATIENT)) {
            preparedStatement.setString(1, patient.getFirstName());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3, patient.getDiagnosis());
            preparedStatement.setDate(4, patient.getBirthDate());
            preparedStatement.setInt(5, patient.getId());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Can't update patient." + e.getMessage());
            return false;
        } finally {
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public boolean updateTreatment(PatientHasDoctor phs) {
        CONNECTION_LOCK.lock();
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TREATMENT)) {
            preparedStatement.setString(1, phs.getTreatment());
            preparedStatement.setInt(2, phs.getDoctorId());
            preparedStatement.setInt(3, phs.getPatientId());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Can't set treatment" + e.getMessage());
            return false;
        } finally {
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public boolean insertDoctor(Doctor doctor, User user) {
        ResultSet resultSet = null;
        CONNECTION_LOCK.lock();
        try(PreparedStatement psLogin = connection.prepareStatement(INSERT_INTO_USER_TABLE, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psDoctor = connection.prepareStatement(INSERT_INTO_DOCTOR_TABLE)){
            psLogin.setString(1, user.getUsername());
            psLogin.setString(2, user.getPassword());
            psLogin.setString(3, user.getRole().toString());

            if (psLogin.executeUpdate() != 1) return false;

            resultSet = psLogin.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
            psDoctor.setString(1, doctor.getFirstName());
            psDoctor.setString(2, doctor.getLastName());
            psDoctor.setString(3, doctor.getPosition());
            psDoctor.setInt(4, user.getId());

            if(psDoctor.executeUpdate() != 1) return false;
        } catch (SQLException e) {
            return false;
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public boolean insertNurse(Nurse nurse, User user) {
        ResultSet resultSet = null;
        CONNECTION_LOCK.lock();
        try(PreparedStatement psLogin = connection.prepareStatement(INSERT_INTO_USER_TABLE, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psDoctor = connection.prepareStatement(INSERT_INTO_NURSE_TABLE)){
            psLogin.setString(1, user.getUsername());
            psLogin.setString(2, user.getPassword());
            psLogin.setString(3, user.getRole().toString());

            if (psLogin.executeUpdate() != 1) return false;

            resultSet = psLogin.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
            psDoctor.setString(1, nurse.getFirstName());
            psDoctor.setString(2, nurse.getLastName());
            psDoctor.setInt(3, user.getId());

            if(psDoctor.executeUpdate() != 1) return false;
        } catch (SQLException e) {
            System.err.println("Nurse insertion failed" + e.getMessage());
            return false;
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public boolean insertPatient(Patient patient) {
        CONNECTION_LOCK.lock();
        try(PreparedStatement psPatient = connection.prepareStatement(INSERT_INTO_PATIENT_TABLE)){
            psPatient.setString(1, patient.getFirstName());
            psPatient.setString(2, patient.getLastName());
            psPatient.setString(3, patient.getDiagnosis());
            psPatient.setDate(4, patient.getBirthDate());
            psPatient.setBoolean(5, false);
            if (psPatient.executeUpdate() != 1) return false;
        } catch (SQLException e) {
            System.err.println("Patient insertion failed" + e.getMessage());
            return false;
        } finally {
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public boolean insertPatientHasDoctor(PatientHasDoctor phd) {
        CONNECTION_LOCK.lock();
        try(PreparedStatement psPatient = connection.prepareStatement(INSERT_INTO_PATIENT_HAS_DOCTOR_TABLE)){
            psPatient.setInt(1, phd.getDoctorId());
            psPatient.setInt(2, phd.getPatientId());
            if (psPatient.executeUpdate() != 1) return false;
        } catch (SQLException e) {
            System.err.println("Appointment insertion failed" + e.getMessage());
            return false;
        } finally {
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public List<DiseaseHistory> getHospitalCard(int patientId) {
        ResultSet resultSet = null;
        List<DiseaseHistory> hospitalCard = new ArrayList<>();
        CONNECTION_LOCK.lock();
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
            System.err.println("Can't find disease history." + e.getMessage());
            return Collections.emptyList();
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return hospitalCard;
    }

    public List<Doctor> getSortedDoctors(String columnName) {
        ResultSet resultSet = null;
        List<Doctor> doctors = new ArrayList<>();
        CONNECTION_LOCK.lock();
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
            System.err.println("Can't find doctors" + e.getMessage());
            return Collections.emptyList();
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return doctors;
    }

    public List<Patient> getSortedPatients(String columnName) {
        ResultSet resultSet = null;
        List<Patient> patients = new ArrayList<>();
        CONNECTION_LOCK.lock();
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
            System.err.println("Can't find patients" + e.getMessage());
            return Collections.emptyList();
        } finally {
            close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return patients;
    }
}
