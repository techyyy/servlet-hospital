package com.hospital.Hospital.web.command.impl.outofcontrol;

import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.user.User;
import com.hospital.Hospital.util.Hashing;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.command.impl.nurse.Appointment;
import com.hospital.Hospital.web.constants.ServletPaths;
import com.mysql.cj.log.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * Login command
 *
 */
public class Login extends Command {

    private static final Logger LOG = Logger.getLogger(String.valueOf(Login.class));
    private static UserDAO userDAO;

    public Login() {userDAO = new UserDAO();}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LOG.info("Executing " + this.getClass().getSimpleName() + " command");
        String forward = null;
        HttpSession session = request.getSession();

        String login = request.getParameter("Username");
        String password = Hashing.hashMD5(request.getParameter("Password"));

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
