package com.hospital.Hospital.web.command.impl.nurse;

import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * Appointment command
 *
 */
public class Appointment extends Command {
    private static final Logger LOG = Logger.getLogger(String.valueOf(Appointment.class));
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        return null;
    }
}
