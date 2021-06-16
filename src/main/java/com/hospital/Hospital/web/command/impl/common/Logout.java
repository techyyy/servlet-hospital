package com.hospital.Hospital.web.command.impl.common;

import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;


/**
 *
 * Logout command
 *
 */
public class Logout extends Command {
    private static final Logger LOG = Logger.getLogger(String.valueOf(Logout.class));
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return JspPaths.LOGIN_PAGE;
    }
}
