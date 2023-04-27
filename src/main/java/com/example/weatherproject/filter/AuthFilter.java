package com.example.weatherproject.filter;

import com.example.weatherproject.exception.ExceptionBody;
import com.example.weatherproject.exception.user_exception.UserAlreadyEnterToSystemException;
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
        if(method.equals("GET")){
            workingWithGetHttp(servletRequest,servletResponse,filterChain);
        }else if(method.equals("POST")) {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    private void workingWithGetHttp(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
            String login = servletRequest.getParameter("login");
            String password = servletRequest.getParameter("password");
            if(login == null || password == null || login.length() == 0 || password.length() == 0) throw new ValidationDataException();
            HttpSession session = ((HttpServletRequest)servletRequest).getSession(true);
            Long userId = (Long) session.getAttribute("userId");
            if(userId != null) throw new UserAlreadyEnterToSystemException();
            servletRequest.setAttribute("session",session);
            filterChain.doFilter(servletRequest,servletResponse);
            boolean errorExist = (boolean) servletRequest.getAttribute("error");
            if(errorExist){

            }else {
                UserDto userAfterCheck = (UserDto) servletRequest.getAttribute("user");
                System.out.println(userAfterCheck.getLogin());
                session.setAttribute("userId",userAfterCheck.getId());
                session.setMaxInactiveInterval(7200);
                Cookie cookie = new Cookie("sessionId", session.getId());
                ((HttpServletResponse)servletResponse).addCookie(cookie);
                servletRequest.getRequestDispatcher("/auth/mane").forward(servletRequest, servletResponse);
            }
        }catch (UserAlreadyEnterToSystemException e) {
            servletRequest.getRequestDispatcher("/auth/mane").forward(servletRequest, servletResponse);
        }catch (ValidationDataException e){
            servletResponse = getReadyResponse(400,"validation exception!",servletResponse);
            servletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
        }
    }

    private ServletResponse getReadyResponse(int code, String message, ServletResponse response) throws IOException {
        String responseError = gson.toJson(new ExceptionBody(code,message));
        response.getWriter().write(responseError);
        return response;
    }
}
