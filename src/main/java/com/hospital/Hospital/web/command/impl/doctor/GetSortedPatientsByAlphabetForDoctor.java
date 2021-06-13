package com.hospital.Hospital.web.command.impl.doctor;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.Doctor;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;
import static com.hospital.Hospital.web.constants.StringConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetSortedPatientsByAlphabetForDoctor extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        Doctor doctor = new UserDAO().getDoctorByLoginId(userId);
        session.setAttribute("doctorId", doctor.getId());
        List<Patient> patients = new PatientDAO().findSortedPatientsForDoctor(doctor.getId(), SQL_SORT_BY_ALPHABET);
        request.setAttribute("patients", patients);
        return JspPaths.VIEW_PATIENTS;
    }
}
