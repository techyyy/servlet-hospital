package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;
import static com.hospital.Hospital.web.constants.StringConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetSortedPatientsByBirthDate extends Command {

    private final PatientDAO patientDAO;

    public GetSortedPatientsByBirthDate() {patientDAO = new PatientDAO();}


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Patient> patients = patientDAO.getSortedPatients(SQL_SORT_BY_BIRTH_DATE);
        request.setAttribute("patients", patients);
        return JspPaths.VIEW_PATIENTS;
    }
}
