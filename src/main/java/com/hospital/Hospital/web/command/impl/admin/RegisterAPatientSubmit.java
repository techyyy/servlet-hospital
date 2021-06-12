package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class RegisterAPatientSubmit extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
        Patient patient = new Patient(0,
                request.getParameter("FirstName"),
                request.getParameter("LastName"),
                request.getParameter("Diagnosis"),
                Date.valueOf(request.getParameter("BirthDate")));
        new UserDAO().insertPatient(patient);
        return ServletPaths.SERVLET_ADMIN_PANEL;
    }
}
