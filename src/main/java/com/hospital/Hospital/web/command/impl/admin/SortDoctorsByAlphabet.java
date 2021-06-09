package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.db.HospitalManagerDAO;
import com.hospital.Hospital.model.Doctor;
import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;
import static com.hospital.Hospital.web.constants.StringConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.hospital.Hospital.util.Pagination.numberOfPages;
import static com.hospital.Hospital.util.Pagination.sublist;
import static com.hospital.Hospital.web.constants.NumberConstants.NUMBER_OF_RECORDS_PER_PAGE;

public class SortDoctorsByAlphabet extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
        HospitalManagerDAO hospitalManagerDAO = HospitalManagerDAO.getInstance();
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int offset = (currentPage-1)*NUMBER_OF_RECORDS_PER_PAGE;
        List<Doctor> doctors = sublist(hospitalManagerDAO.getSortedDoctors(SQL_SORT_BY_BIRTH_DATE), offset, offset+NUMBER_OF_RECORDS_PER_PAGE);
        request.setAttribute("numberOfPages", numberOfPages(doctors));
        request.setAttribute("doctors", doctors);
        return JspPaths.VIEW_ALL_DOCTORS;
    }
}
