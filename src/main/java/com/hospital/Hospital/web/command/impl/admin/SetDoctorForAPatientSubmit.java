package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.HospitalManagerDAO;
import com.hospital.Hospital.model.PatientHasDoctor;
import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetDoctorForAPatientSubmit extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
        PatientHasDoctor phd = new PatientHasDoctor(Integer.parseInt(request.getParameter("DoctorID")),
                Integer.parseInt(request.getParameter("PatientID")),
                "");
        HospitalManagerDAO dao = HospitalManagerDAO.getInstance();
        dao.insertPatientHasDoctor(phd);
        return ServletPaths.SERVLET_ADMIN_PANEL;
    }
}
