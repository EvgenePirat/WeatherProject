package com.example.weatherproject.filter;

import com.example.weatherproject.exception.ExceptionBody;
import com.example.weatherproject.exception.session_exception.SessionNotFoundException;
import com.example.weatherproject.service.SessionService;
import com.example.weatherproject.service.impl.SessionServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class SecurityFilter implements Filter {

    private SessionService sessionService = new SessionServiceImpl();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();
            Optional<Cookie> cookieWithSession = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("sessionId")).findFirst();
            if(cookieWithSession.isPresent() == false) servletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
            String sessionId = cookieWithSession.get().getValue();
            System.out.println(sessionId);
            if(sessionService.checkSession(sessionId) == false) servletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (SessionNotFoundException e){
            servletRequest.setAttribute("bodyError", new ExceptionBody(400, "Session not found!"));
            servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
        }
    }
}
