package com.hospital.Hospital.web.command.impl.admin;

import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetDoctorForAPatient extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
        String message = "";
        request.getSession().setAttribute("error", message);
        return JspPaths.SET_DOCTOR_FOR_A_PATIENT;
    }
}
