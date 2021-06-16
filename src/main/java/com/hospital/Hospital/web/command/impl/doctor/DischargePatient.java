package com.hospital.Hospital.web.command.impl.doctor;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.util.Validation;
import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


/**
 *
 * Discharge patient command
 *
 */
public class DischargePatient extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(DischargePatient.class));
    private final PatientDAO patientDAO;

    public DischargePatient() {patientDAO = new PatientDAO();}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        int patientID;
        try {
            patientID = Validation.getIntField(request, "patientId");
        }catch (IllegalArgumentException e) {
            return JspPaths.ERROR_PAGE;
        }
        patientDAO.dischargePatient(patientID);
        return ServletPaths.SERVLET_VIEW_PATIENTS_BY_DOCTOR;
    }
}
