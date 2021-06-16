package com.hospital.Hospital.web.command.impl.common;

import com.hospital.Hospital.web.MainServlet;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * Main page command
 *
 */
public class MainPage extends Command {
    private static final Logger LOG = Logger.getLogger(String.valueOf(MainPage.class));
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        return JspPaths.MAIN_PAGE;
    }
}
