package servletTests;

import com.hospital.Hospital.db.ConnectionPool;
import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.model.user.Role;
import com.hospital.Hospital.model.user.User;
import com.hospital.Hospital.util.Hashing;
import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.command.CommandManager;
import com.hospital.Hospital.web.command.impl.outofcontrol.Login;
import com.hospital.Hospital.web.constants.ServletPaths;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginTest {

    @Before
    public void setUpTest() throws Exception {
        UserDAO userDAO = mock(UserDAO.class);
        User user = new User.UserBuilder("doctor", Hashing.hashMD5("pass"), Role.DOCTOR).build();
        when(userDAO.getUserByLogin("doctor")).thenReturn(user);
        Whitebox.setInternalState(Login.class, "userDAO", userDAO);
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setURL("jdbc:mysql://127.0.0.1:3306/hospital?user=root&password=admin");
        ConnectionPool.setDataSource(dataSource);

    }

    @Test
    public void signInSuccessTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("Username")).thenReturn("doctor");
        when(request.getParameter("Password")).thenReturn("pass");
        when(request.getSession()).thenReturn(session);
        Command command = CommandManager.get("login");
        assertEquals(command.execute(request, response), ServletPaths.SERVLET_MAIN_PAGE);
    }

    @Test
    public void signInFailTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("Username")).thenReturn("doctorino");
        when(request.getParameter("Password")).thenReturn("pass");
        when(request.getSession()).thenReturn(session);
        Command command = CommandManager.get("login");
        assertNotEquals(command.execute(request, response), ServletPaths.SERVLET_MAIN_PAGE);
    }

}
