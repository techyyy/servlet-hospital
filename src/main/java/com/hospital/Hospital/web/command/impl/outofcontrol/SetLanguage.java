package com.hospital.Hospital.web.command.impl.outofcontrol;

import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * Set language command
 *
 */
public class SetLanguage extends Command {
    private static final Logger LOG = Logger.getLogger(String.valueOf(SetLanguage.class));
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        request.getSession().setAttribute("lang", request.getParameter("locale"));
        String uri = request.getParameter("uri");
        String query = request.getParameter("query");
        if(uri.equals("")) {
            return ServletPaths.SERVLET_LOGIN_PAGE;
        } else {
            return uri+"?"+query;
        }
    }
}
