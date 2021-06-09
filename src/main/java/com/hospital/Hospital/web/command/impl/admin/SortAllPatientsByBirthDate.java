package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.HospitalManagerDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;
import static com.hospital.Hospital.web.constants.StringConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SortAllPatientsByBirthDate extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
        HospitalManagerDAO hospitalManagerDAO = HospitalManagerDAO.getInstance();
        List<Patient> patients = hospitalManagerDAO.getSortedPatients(SQL_SORT_BY_BIRTH_DATE);
        request.setAttribute("patients", patients);
        return JspPaths.VIEW_PATIENTS;
    }
}
