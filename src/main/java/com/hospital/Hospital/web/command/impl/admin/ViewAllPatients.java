package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * View all patients command
 *
 */
public class ViewAllPatients extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(ViewAllPatients.class));
    private final PatientDAO patientDAO;

    public ViewAllPatients() {patientDAO = new PatientDAO();}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        List<Patient> patients = patientDAO.findAllPatients();
        request.setAttribute("patients", patients);
        return JspPaths.VIEW_PATIENTS;
    }
}
