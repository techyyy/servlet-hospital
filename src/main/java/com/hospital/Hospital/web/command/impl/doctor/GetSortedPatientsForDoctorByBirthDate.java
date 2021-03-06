package com.hospital.Hospital.web.command.impl.doctor;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.Doctor;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;
import static com.hospital.Hospital.web.constants.StringConstants.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * Get sorted patients for doctor by birth date command
 *
 */
public class GetSortedPatientsForDoctorByBirthDate extends Command {
    private static final Logger LOG = Logger.getLogger(String.valueOf(GetSortedPatientsForDoctorByBirthDate.class));
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        Doctor doctor = new UserDAO().getDoctorByLoginId(userId);
        session.setAttribute("doctorId", doctor.getId());
        List<Patient> patients = new PatientDAO().findSortedPatientsForDoctor(doctor.getId(), SQL_SORT_BY_BIRTH_DATE);
        request.setAttribute("patients", patients);
        return JspPaths.VIEW_PATIENTS;
    }
}
