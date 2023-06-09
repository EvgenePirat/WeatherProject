package com.example.weatherproject.filter;

import com.example.weatherproject.exception.ExceptionBody;
import com.example.weatherproject.exception.validation_exception.ValidationDataException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class WeatherFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String method = ((HttpServletRequest) servletRequest).getMethod();
        if(method.equals("POST")){
            workingWithPostRequest(servletRequest,servletResponse);
            filterChain.doFilter(servletRequest,servletResponse);
            checkError(servletRequest,servletResponse);
        } else{
            filterChain.doFilter(servletRequest,servletResponse);
            checkError(servletRequest,servletResponse);
        }
    }

    private void checkError(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        if (servletRequest.getAttribute("bodyError") != null) {
            servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("/security/main").forward(servletRequest, servletResponse);
        }
    }

    private void workingWithPostRequest(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try{
            String nameCity = servletRequest.getParameter("city");
            if(nameCity == null || nameCity.length() == 0) throw new ValidationDataException();
        }catch (ValidationDataException e){
            servletRequest.setAttribute("bodyError", new ExceptionBody(400, "Validation exception\nCity not found in request"));
            servletRequest.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
        }
    }
}
