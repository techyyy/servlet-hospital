package com.hospital.Hospital.web.filters;

import com.hospital.Hospital.model.user.Role;
import com.hospital.Hospital.web.constants.JspPaths;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 *
 * Access filter
 *
 */
@WebFilter(filterName = "Filter")
public class AccessFilter implements Filter {
    private static Map<Role, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<>();
    private static List<String> outOfControl = new ArrayList<>();

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (accessAllowed(request)) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher(JspPaths.ERROR_PAGE).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }
        if (outOfControl.contains(commandName)) {
            return true;
        }
        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }
        Role userRole = (Role) session.getAttribute("userRole");
        if (userRole == null) {
            return commons.contains(commandName);
        }
        return accessMap.get(userRole).contains(commandName) || commons.contains(commandName);
    }

    public void init(FilterConfig fConfig) {
        if(fConfig.getInitParameterNames().hasMoreElements()) {
            accessMap.put(Role.ADMIN, asList(fConfig.getInitParameter("admin")));
            accessMap.put(Role.DOCTOR, asList(fConfig.getInitParameter("doctor")));
            accessMap.put(Role.NURSE, asList(fConfig.getInitParameter("nurse")));
            commons = asList(fConfig.getInitParameter("common"));
            outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        }
    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
