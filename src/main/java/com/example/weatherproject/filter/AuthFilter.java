package com.example.weatherproject.filter;

import com.example.weatherproject.exception.ExceptionBody;
import com.example.weatherproject.exception.validation_exception.ValidationDataException;
import com.example.weatherproject.model.UserDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String method = ((HttpServletRequest) servletRequest).getMethod();
        if (method.equals("GET")) {
            workingWithGetHttp(servletRequest, servletResponse, filterChain);
        } else if (method.equals("POST")) {
            workingWithPostHttp(servletRequest, servletResponse, filterChain);
        }
    }

    private void workingWithPostHttp(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(servletRequest, servletResponse);
        if (servletRequest.getAttribute("bodyError") != null) {
            servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
        }
    }

    private void workingWithGetHttp(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        processValidationForGet(servletRequest,servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
        if (servletRequest.getAttribute("bodyError") != null) {
            servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
        } else {
            UserDto userAfterCheck = (UserDto) servletRequest.getAttribute("user");
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
            session.setAttribute("userId", userAfterCheck.getId());
            session.setMaxInactiveInterval(7200);
            System.out.println(session.getId());
            Cookie cookie = new Cookie("sessionId", session.getId());
            cookie.setPath("/WeatherProject_war_exploded/security/weather");
            cookie.setHttpOnly(true);
            ((HttpServletResponse) servletResponse).addCookie(cookie);
            servletRequest.getRequestDispatcher("/security/main").forward(servletRequest, servletResponse);
        }
    }

    private void processValidationForGet(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try {
            String login = servletRequest.getParameter("login");
            String password = servletRequest.getParameter("password");
            if (login == null || password == null || login.length() == 0 || password.length() == 0)
                throw new ValidationDataException();
        } catch (ValidationDataException e) {
            servletResponse = getReadyResponse(400, "validation exception!", servletResponse);
            servletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
        }
    }

    private ServletResponse getReadyResponse(int code, String message, ServletResponse response) throws IOException {
        String responseError = gson.toJson(new ExceptionBody(code, message));
        response.getWriter().write(responseError);
        return response;
    }
}
