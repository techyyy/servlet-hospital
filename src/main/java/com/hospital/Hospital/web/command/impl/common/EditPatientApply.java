package com.hospital.Hospital.web.command.impl.common;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

public class EditPatientApply extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        new PatientDAO().updatePatient(new Patient.PatientBuilder(request.getParameter("FirstName"), request.getParameter("LastName"))
                .diagnosis(request.getParameter("Diagnosis"))
                .birthDate(LocalDate.parse(request.getParameter("BirthDate")))
                .id(Integer.parseInt(request.getParameter("id")))
                .build());
        if(session.getAttribute("userRole").toString().equals("DOCTOR")) {
            return ServletPaths.SERVLET_VIEW_PATIENTS_BY_DOCTOR;
        } else if(session.getAttribute("userRole").toString().equals("ADMIN")) {
            return ServletPaths.SERVLET_VIEW_ALL_PATIENTS;
        } else {
            return null;
        }
    }
}
