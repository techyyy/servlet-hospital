package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Logger;


/**
 *
 * Register a patient submit command
 *
 */
public class RegisterAPatientSubmit extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(RegisterAPatientSubmit.class));
    private final UserDAO userDAO;

    public RegisterAPatientSubmit() {userDAO = new UserDAO();}


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        userDAO.insertPatient(new Patient.PatientBuilder(request.getParameter("FirstName"), request.getParameter("LastName"))
                .diagnosis(request.getParameter("Diagnosis"))
                .birthDate(LocalDate.parse(request.getParameter("BirthDate")))
                .build());
        return ServletPaths.SERVLET_ADMIN_PANEL;
    }
}
