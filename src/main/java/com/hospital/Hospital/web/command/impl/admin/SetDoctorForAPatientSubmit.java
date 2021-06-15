package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.PatientAssignment;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetDoctorForAPatientSubmit extends Command {

    private final UserDAO userDAO;

    public SetDoctorForAPatientSubmit() {userDAO = new UserDAO();}


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PatientAssignment phd = new PatientAssignment.PatientAssignmentBuilder(Integer.parseInt(request.getParameter("DoctorID")),
                Integer.parseInt(request.getParameter("PatientID")))
                .build();
        userDAO.insertPatientAssignment(phd);
        return ServletPaths.SERVLET_ADMIN_PANEL;
    }
}
