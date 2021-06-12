package com.hospital.Hospital.db;

public class Queries {
    public static final String SELECT_ALL_PATIENTS = "SELECT * FROM patient WHERE isDischarged = false";
    public static final String SELECT_ALL_PATIENTS_ORDER_BY = "SELECT * FROM patient WHERE isDischarged = false ORDER BY %s";
    public static final String SELECT_PATIENTS_BY_DOCTOR_ID = "SELECT p.id, p.firstName, p.lastName, p.diagnosis, p.birthDate FROM patient_has_doctor pd\n" +
            "            JOIN doctor d ON pd.doctor_id = d.id\n" +
            "            JOIN patient p ON pd.patient_id = p.id\n" +
            "            WHERE d.id = ? AND p.isDischarged = false;";
    public static final String SELECT_PATIENTS_BY_DOCTOR_ID_ORDER_BY = "SELECT p.id, p.firstName, p.lastName, p.diagnosis, p.birthDate FROM patient_has_doctor pd\n" +
            "            JOIN doctor d ON pd.doctor_id = d.id\n" +
            "            JOIN patient p ON pd.patient_id = p.id\n" +
            "            WHERE d.id = ? AND p.isDischarged = false " +
            "            ORDER BY %s";
    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM login WHERE username = ?";
    public static final String SELECT_DOCTOR_BY_USER_ID = "SELECT * FROM doctor WHERE login_id = ?";
    public static final String SELECT_ALL_DOCTORS = "SELECT * FROM doctor";
    public static final String SELECT_ALL_DOCTORS_ORDER_BY = "SELECT * FROM doctor ORDER BY %s";
    public static final String SELECT_PATIENT_BY_ID = "SELECT * FROM patient WHERE id = ?";
    public static final String SELECT_DOCTOR_BY_ID = "SELECT * FROM doctor WHERE id = ?";
    public static final String SELECT_PATIENT_CARD = "SELECT patient.firstName,patient.lastName,diagnosis,doctor.firstName,doctor.lastName,position,treatment" +
            " FROM patient INNER JOIN patient_has_doctor " +
            "ON patient.id = patient_has_doctor.patient_id " +
            "INNER JOIN doctor " +
            "ON doctor.id = patient_has_doctor.doctor_id" +
            " WHERE patient.id = ?";

    public static final String UPDATE_PATIENT = "UPDATE patient "
            + "SET firstName = ?, lastName = ?, diagnosis = ?,birthDate = ? "
            + "WHERE id = ?;";
    public static final String UPDATE_TREATMENT = "UPDATE patient_has_doctor " +
            "SET treatment = ? " +
            "WHERE doctor_id = ? AND patient_id = ?";
    public static final String UPDATE_DISCHARGE = "UPDATE patient " +
            "SET isDischarged = true " +
            "WHERE id = ?";

    public static final String INSERT_INTO_USER_TABLE = "INSERT INTO login VALUES(0, ?, ?, ?)";
    public static final String INSERT_INTO_DOCTOR_TABLE = "INSERT INTO doctor VALUES(0,?,?,?,?)";
    public static final String INSERT_INTO_NURSE_TABLE = "INSERT INTO nurse VALUES(0,?,?,?)";
    public static final String INSERT_INTO_PATIENT_TABLE = "INSERT INTO patient VALUES(0,?,?,?,?,?)";
    public static final String INSERT_INTO_PATIENT_HAS_DOCTOR_TABLE = "INSERT INTO patient_has_doctor VALUES(?,?,'')";

}
