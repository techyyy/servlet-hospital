package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.Doctor;
import com.hospital.Hospital.model.user.Role;
import com.hospital.Hospital.model.user.User;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterADoctorSubmit extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = new User.UserBuilder(request.getParameter("Username"), request.getParameter("Password"), Role.DOCTOR).build();
        Doctor doctor = new Doctor.DoctorBuilder(request.getParameter("FirstName"), request.getParameter("LastName"))
                .position(request.getParameter("Position"))
                .build();
        new UserDAO().insertDoctor(doctor, user);

        return ServletPaths.SERVLET_ADMIN_PANEL;
    }
}
