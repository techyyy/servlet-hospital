package com.hospital.Hospital.web;

import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.command.CommandManager;
import com.hospital.Hospital.web.command.impl.NullCase;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * Main controller
 *
 */
public class MainServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(String.valueOf(MainServlet.class));

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response, ActionType.GET);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response, ActionType.POST);
    }

    private void process(HttpServletRequest request, HttpServletResponse response, ActionType actionType)
            throws ServletException, IOException {
        LOG.info("Start processing in Controller");
        HttpSession session = request.getSession();
        String commandName = request.getParameter("command");
        LOG.info("Request parameter: 'command' = " + commandName);
        Command command = CommandManager.get(commandName);
        if(command == null) {
            command = new NullCase();
        }
        LOG.info("Obtained 'command' = " + command);
        String path = command.execute(request, response);

        if (path == null) {
            LOG.info("Redirect to address == null");
            LOG.info("Controller processing finished");
            response.sendRedirect("/");
        } else {
            if (actionType == ActionType.GET) {
                LOG.info("Forward to page = " + path);
                LOG.info("Controller processing finished");
                request.getRequestDispatcher(path).forward(request, response);
            } else if (actionType == ActionType.POST) {
                LOG.info("Redirect to address = " + path);
                LOG.info("Controller processing finished");
                response.sendRedirect(path);
            }
        }

    }
}
