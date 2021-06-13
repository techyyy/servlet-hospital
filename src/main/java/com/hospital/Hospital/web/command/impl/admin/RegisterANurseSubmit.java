package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.Nurse;
import com.hospital.Hospital.model.user.Role;
import com.hospital.Hospital.model.user.User;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterANurseSubmit extends Command {

    private final UserDAO userDAO;

    public RegisterANurseSubmit() {userDAO = new UserDAO();}


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User.UserBuilder(request.getParameter("Username"), request.getParameter("Password"), Role.NURSE).build();
        Nurse nurse = new Nurse.NurseBuilder(request.getParameter("FirstName"), request.getParameter("LastName")).build();
        userDAO.insertNurse(nurse, user);
        return ServletPaths.SERVLET_ADMIN_PANEL;
    }
}
