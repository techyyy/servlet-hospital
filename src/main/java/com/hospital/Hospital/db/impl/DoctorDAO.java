package com.hospital.Hospital.db.impl;

import com.hospital.Hospital.db.ConnectionPool;
import com.hospital.Hospital.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.hospital.Hospital.db.Queries.*;

public class DoctorDAO {
    private static Connection connection;

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
            ConnectionPool.close(resultSet);
            CONNECTION_LOCK.unlock();
        }
        return patients;
    }
}
