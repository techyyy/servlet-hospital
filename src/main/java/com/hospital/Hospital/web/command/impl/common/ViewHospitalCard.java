package com.hospital.Hospital.web.command.impl.common;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.DiseaseHistory;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewHospitalCard extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<DiseaseHistory> hospitalCard = new UserDAO().getHospitalCard(Integer.parseInt(request.getParameter("patientId")));
        request.setAttribute("hospitalCard", hospitalCard);
        return JspPaths.VIEW_HOSPITAL_CARD;
    }
}
