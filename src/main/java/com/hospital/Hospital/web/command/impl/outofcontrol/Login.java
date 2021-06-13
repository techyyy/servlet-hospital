package com.hospital.Hospital.web.command.impl.outofcontrol;

import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.user.User;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.constants.ServletPaths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends Command {

    private final UserDAO userDAO;

    public Login() {userDAO = new UserDAO();}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String forward = null;
        HttpSession session = request.getSession();

        String login = request.getParameter("Username");
        String password = request.getParameter("Password");

        User user = userDAO.getUserByLogin(login);

        if(user != null) {
            if (user.getPassword().equals(password)) {
                forward = ServletPaths.SERVLET_MAIN_PAGE;
                session.setAttribute("userRole", user.getRole());
                session.setAttribute("userId", user.getId());
                session.setAttribute("userIsLoggedIn", "true");
            } else {
                forward = "/";
                session.setAttribute("userIsLoggedIn", "false");
            }
        }
        return forward;
    }
}
