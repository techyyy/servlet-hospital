package com.hospital.Hospital.web.command.impl.common;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.model.PatientHasDoctor;
import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetTreatmentForPatientApply extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String treatment = request.getParameter("Treatment");
        if(!treatment.isEmpty()) {
            PatientHasDoctor phs = new PatientHasDoctor(
                    Integer.parseInt(session.getAttribute("doctorId").toString()),
                    Integer.parseInt(request.getParameter("patientId")),
                    treatment);
            new PatientDAO().updateTreatment(phs);
        }
        return ServletPaths.SERVLET_VIEW_PATIENTS_BY_DOCTOR;

    }
}
