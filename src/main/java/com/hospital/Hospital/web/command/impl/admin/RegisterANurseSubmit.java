package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.HospitalManagerDAO;
import com.hospital.Hospital.model.Doctor;
import com.hospital.Hospital.model.Nurse;
import com.hospital.Hospital.model.user.Role;
import com.hospital.Hospital.model.user.User;
import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterANurseSubmit extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
        User user = new User(0,
                request.getParameter("Username"),
                request.getParameter("Password"),
                Role.DOCTOR);
        Nurse nurse = new Nurse(0,
                request.getParameter("FirstName"),
                request.getParameter("LastName"),
                0);
        HospitalManagerDAO hospitalManagerDAO = HospitalManagerDAO.getInstance();
        hospitalManagerDAO.insertNurse(nurse, user);
        return ServletPaths.SERVLET_ADMIN_PANEL;
    }
}
