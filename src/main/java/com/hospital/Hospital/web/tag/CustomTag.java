package com.hospital.Hospital.web.tag;

import com.hospital.Hospital.model.Patient;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CustomTag extends SimpleTagSupport {
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println("<td>" + patient.getFirstName() + "</td>");
        out.println("<td>" + patient.getLastName() + "</td>");
        out.println("<td>" + patient.getDiagnosis() + "</td>");
        out.println("<td>" + patient.getBirthDate() + "</td>");
    }
}
