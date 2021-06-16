package com.hospital.Hospital.web.command.impl.common;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.util.Validation;
import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * Edit patient command
 *
 */
public class EditPatient extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(EditPatient.class));
    private final PatientDAO patientDAO;

    public EditPatient() {patientDAO = new PatientDAO();}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        int patientID;
        try {
            patientID = Validation.getIntField(request, "patientId");
        }catch (IllegalArgumentException e) {
            return JspPaths.ERROR_PAGE;
        }
        Patient patient = patientDAO.getPatientById(patientID);
        request.setAttribute("patient", patient);
        return JspPaths.EDIT_PATIENT;
    }
}
