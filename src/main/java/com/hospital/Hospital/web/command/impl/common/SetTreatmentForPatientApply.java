package com.hospital.Hospital.web.command.impl.common;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.model.PatientAssignment;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetTreatmentForPatientApply extends Command {

    private final PatientDAO patientDAO;

    public SetTreatmentForPatientApply() {patientDAO = new PatientDAO();}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String treatment = request.getParameter("Treatment");
        if(!treatment.isEmpty()) {
            PatientAssignment pa = new PatientAssignment.PatientAssignmentBuilder(Integer.parseInt(session.getAttribute("doctorId").toString()),
                    Integer.parseInt(request.getParameter("patientId")))
                    .treatment(treatment)
                    .build();
            patientDAO.updateTreatment(pa);
        }
        return ServletPaths.SERVLET_VIEW_PATIENTS_BY_DOCTOR;

    }
}
