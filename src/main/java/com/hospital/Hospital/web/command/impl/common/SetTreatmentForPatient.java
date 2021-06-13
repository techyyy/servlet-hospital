package com.hospital.Hospital.web.command.impl.common;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetTreatmentForPatient extends Command {

    private final PatientDAO patientDAO;

    public SetTreatmentForPatient() {patientDAO = new PatientDAO();}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Patient patient = patientDAO.getPatientById(Integer.parseInt(request.getParameter("patientId")));
        request.setAttribute("patient", patient);
        return JspPaths.SET_TREATMENT_FOR_PATIENT;
    }
}
