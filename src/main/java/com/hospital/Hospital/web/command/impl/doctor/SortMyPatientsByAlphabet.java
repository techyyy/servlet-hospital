package com.hospital.Hospital.web.command.impl.doctor;

import com.hospital.Hospital.db.HospitalManagerDAO;
import com.hospital.Hospital.model.Doctor;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;
import static com.hospital.Hospital.web.constants.StringConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SortMyPatientsByAlphabet extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
        HttpSession session = request.getSession();
        HospitalManagerDAO patientDAO = HospitalManagerDAO.getInstance();
        HospitalManagerDAO loginDAO = HospitalManagerDAO.getInstance();
        int userId = (int) session.getAttribute("userId");
        Doctor doctor = loginDAO.getDoctorByLoginId(userId);
        session.setAttribute("doctorId", doctor.getId());
        List<Patient> patients = patientDAO.findSortedPatientsForDoctor(doctor.getId(), SQL_SORT_BY_ALPHABET);
        request.setAttribute("patients", patients);
        return JspPaths.VIEW_PATIENTS;
    }
}
