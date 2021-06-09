package com.hospital.Hospital.web.command.impl.doctor;

import com.hospital.Hospital.db.HospitalManagerDAO;
import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DischargePatient extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
        HospitalManagerDAO hospitalManagerDAO = HospitalManagerDAO.getInstance();
        hospitalManagerDAO.dischargePatient(Integer.parseInt(request.getParameter("patientId")));
        return ServletPaths.SERVLET_VIEW_PATIENTS_BY_DOCTOR;
    }
}
