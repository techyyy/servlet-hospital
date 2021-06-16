package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.Doctor;
import com.hospital.Hospital.model.user.Role;
import com.hospital.Hospital.model.user.User;
import com.hospital.Hospital.util.Hashing;
import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * Register a doctor submit command
 *
 */
public class RegisterADoctorSubmit extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(RegisterADoctorSubmit.class));
    private final UserDAO userDAO;

    public RegisterADoctorSubmit() {userDAO = new UserDAO();}


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        User user = new User.UserBuilder(request.getParameter("Username"), Hashing.hashMD5(request.getParameter("Password")), Role.DOCTOR)
                .build();
        Doctor doctor = new Doctor.DoctorBuilder(request.getParameter("FirstName"), request.getParameter("LastName"))
                .position(request.getParameter("Position"))
                .build();
        userDAO.insertDoctor(doctor, user);

        return ServletPaths.SERVLET_ADMIN_PANEL;
    }
}
