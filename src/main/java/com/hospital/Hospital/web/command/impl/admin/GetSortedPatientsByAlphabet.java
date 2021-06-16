package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;
import static com.hospital.Hospital.web.constants.StringConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * Get sorted patients by alphabet command
 *
 */
public class GetSortedPatientsByAlphabet extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(GetSortedPatientsByAlphabet.class));
    private final PatientDAO patientDAO;

    public GetSortedPatientsByAlphabet() {patientDAO = new PatientDAO();}


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        List<Patient> patients = patientDAO.getSortedPatients(SQL_SORT_BY_ALPHABET);
        request.setAttribute("patients", patients);
        return JspPaths.VIEW_PATIENTS;
    }
}
