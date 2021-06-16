package com.hospital.Hospital.web.command.impl.common;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.DiseaseHistory;
import com.hospital.Hospital.util.Validation;
import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


/**
 *
 * View hospital card command
 *
 */
public class ViewHospitalCard extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(ViewHospitalCard.class));
    private final UserDAO userDAO;

    public ViewHospitalCard() {userDAO = new UserDAO();}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        int patientID;
        try {
            patientID = Validation.getIntField(request, "patientId");
        }catch (IllegalArgumentException e) {
            return JspPaths.ERROR_PAGE;
        }
        List<DiseaseHistory> hospitalCard = userDAO.getHospitalCard(patientID);
        request.setAttribute("hospitalCard", hospitalCard);
        return JspPaths.VIEW_HOSPITAL_CARD;
    }
}
