package com.hospital.Hospital.web.command.impl.common;

import com.hospital.Hospital.db.HospitalManagerDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditPatient extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
        HospitalManagerDAO hospitalManagerDAO = HospitalManagerDAO.getInstance();
        Patient patient = hospitalManagerDAO.getPatientById(Integer.parseInt(request.getParameter("patientId")));
        request.setAttribute("patient", patient);
        return JspPaths.EDIT_PATIENT;
    }
}
