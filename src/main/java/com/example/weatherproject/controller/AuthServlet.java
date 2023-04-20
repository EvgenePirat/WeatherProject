package com.example.weatherproject.controller;

import com.example.weatherproject.entity.User;
import com.example.weatherproject.exception.ExceptionBody;
import com.example.weatherproject.exception.LoginAlreadyExistException;
import com.example.weatherproject.service.AuthService;
import com.example.weatherproject.service.impl.AuthServiceImpl;
import com.google.gson.Gson;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/auth")
public class AuthServlet extends HttpServlet {

    private AuthService authService = new AuthServiceImpl();

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            User userAfterSave = authService.login(new User(login, password));
            HttpSession session = request.getSession();
            System.out.println(session.getId());
            String userAfterSaveInJson = gson.toJson(userAfterSave);
            response.getWriter().write(userAfterSaveInJson);
        }catch (IOException e){
            response = getReadyResponse(404,"Required form field is missing",response);
        }catch (PersistenceException e){
            response = getReadyResponse(500, "Error with BD",response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try{
            User user = gson.fromJson(request.getReader(), User.class);
            User userAfterSave = authService.registration(user);
            String userAfterSaveInJson = gson.toJson(userAfterSave);
            response.getWriter().write(userAfterSaveInJson);
        }catch (IOException e){
            response = getReadyResponse(404,"Required form field is missing",response);
        }catch (LoginAlreadyExistException e){
            response = getReadyResponse(400,"User with such login already exist!",response);
        }catch (PersistenceException e){
            response = getReadyResponse(500, "Error with BD",response);
        }
    }

    private HttpServletResponse getReadyResponse(int code, String message, HttpServletResponse response) throws IOException {
        response.setStatus(code);
        String responseError = gson.toJson(new ExceptionBody(code,message));
        response.getWriter().write(responseError);
        return response;
    }
}
