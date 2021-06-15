package servletTests;

import com.hospital.Hospital.web.command.Command;
import com.hospital.Hospital.web.command.CommandManager;
import com.hospital.Hospital.web.constants.JspPaths;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CommandTest {

    @Test
    public void testLoginCommand() {

    }

    @Test
    public void testRegisterADoctorCommand() throws ServletException, IOException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        when(request.getParameter("command")).thenReturn("registerADoctor");

        Command command = CommandManager.get(request.getParameter("command"));
        assertEquals(JspPaths.REGISTER_A_DOCTOR, command.execute(request,response));
        System.out.println(command.execute(request,response));
        System.out.println(request.getParameter("command"));
    }


}
