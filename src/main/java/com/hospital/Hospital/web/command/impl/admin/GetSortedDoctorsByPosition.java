package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.Doctor;
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

import static com.hospital.Hospital.util.Pagination.numberOfPages;
import static com.hospital.Hospital.util.Pagination.sublist;
import static com.hospital.Hospital.web.constants.NumberConstants.NUMBER_OF_RECORDS_PER_PAGE;

/**
 *
 * Get sorted doctors by position command
 *
 */
public class GetSortedDoctorsByPosition extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(GetSortedDoctorsByPosition.class));
    private final UserDAO userDAO;

    public GetSortedDoctorsByPosition() {userDAO = new UserDAO(); }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int offset = (currentPage-1)*NUMBER_OF_RECORDS_PER_PAGE;
        List<Doctor> doctors = sublist(userDAO.getSortedDoctors(SQL_SORT_BY_POSITION), offset, offset+NUMBER_OF_RECORDS_PER_PAGE);
        request.setAttribute("numberOfPages", numberOfPages(doctors));
        request.setAttribute("doctors", doctors);
        return JspPaths.VIEW_ALL_DOCTORS;
    }
}
