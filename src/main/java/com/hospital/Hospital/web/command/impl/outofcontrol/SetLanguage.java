package com.hospital.Hospital.web.command.impl.outofcontrol;

import com.hospital.Hospital.web.ActionType;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetLanguage extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ActionType actionType) throws IOException, ServletException {
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
