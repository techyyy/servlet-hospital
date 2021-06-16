package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.PatientAssignment;
import com.hospital.Hospital.util.Validation;
import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;


/**
 *
 * Set doctor for a patient submit command
 *
 */
public class SetDoctorForAPatientSubmit extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(SetDoctorForAPatientSubmit.class));
    private final UserDAO userDAO;

    public SetDoctorForAPatientSubmit() {userDAO = new UserDAO();}


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        int doctorID;
        int patientID;
        try {
            doctorID = Validation.getIntField(request, "DoctorID");
            patientID = Validation.getIntField(request, "PatientID");
        } catch (IllegalArgumentException e) {
            return ServletPaths.SERVLET_SET_DOCTOR_FOR_A_PATIENT;
        }

        PatientAssignment phd = new PatientAssignment.PatientAssignmentBuilder(doctorID, patientID).build();
        try {
            userDAO.insertPatientAssignment(phd);
        } catch (SQLException e) {
            request.getSession().setAttribute("errorMessage", "Invalid patient or doctor id");
            return ServletPaths.SERVLET_SET_DOCTOR_FOR_A_PATIENT;
        }
        return ServletPaths.SERVLET_ADMIN_PANEL;
    }
}
