package com.hospital.Hospital.web.command.impl.doctor;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.Doctor;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


/**
 *
 * View patients by doctor command
 *
 */
public class ViewPatientsByDoctor extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(ViewPatientsByDoctor.class));
    private final PatientDAO patientDAO;

    public ViewPatientsByDoctor() {patientDAO = new PatientDAO();}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        Doctor doctor = new UserDAO().getDoctorByLoginId(userId);
        session.setAttribute("doctorId", doctor.getId());
        List<Patient> patients = patientDAO.findPatientsForDoctor(doctor.getId());
        request.setAttribute("patients", patients);
        return JspPaths.VIEW_PATIENTS;
    }
}
