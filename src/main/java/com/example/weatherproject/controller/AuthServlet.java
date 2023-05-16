package com.example.weatherproject.controller;

import com.example.weatherproject.exception.*;
import com.example.weatherproject.exception.auth_exception.LoginAlreadyExistException;
import com.example.weatherproject.exception.auth_exception.PasswordWrongException;
import com.example.weatherproject.exception.user_exception.UserNotFoundException;
import com.example.weatherproject.model.UserDto;
import com.example.weatherproject.service.AuthService;
import com.example.weatherproject.service.SessionService;
import com.example.weatherproject.service.impl.AuthServiceImpl;
import com.example.weatherproject.service.impl.SessionServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "AuthServlet", value = "/auth/login")
public class AuthServlet extends HttpServlet {

    private final AuthService authService = new AuthServiceImpl();

    private final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    private final SessionService sessionService = new SessionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            UserDto userAfterCheck = authService.login(new UserDto(login, password));
            HttpSession session = request.getSession(true);
            Date date = new Date(System.currentTimeMillis()+(session.getMaxInactiveInterval() * 1000));
            System.out.println(session.getId());
            sessionService.create(session.getId(),userAfterCheck,date);
            request.setAttribute("user",userAfterCheck);
        }catch (PersistenceException e){
            e.printStackTrace();
            request.setAttribute("bodyError", new ExceptionBody(500, "Error with BD"));
        } catch (UserNotFoundException e) {
            request.setAttribute("bodyError", new ExceptionBody(400, "Bad request, login not found!"));
        } catch (PasswordWrongException e) {
            request.setAttribute("bodyError", new ExceptionBody(400, "Password is wrong!"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try{
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            UserDto user = new UserDto(login,password);
            UserDto userAfterSave = authService.registration(user);
            String userAfterSaveInJson = gson.toJson(userAfterSave);
            response.getWriter().write(userAfterSaveInJson);
        }catch (IOException e){
            request.setAttribute("bodyError", new ExceptionBody(400,"Required form field is missing"));
        }catch (LoginAlreadyExistException e){
            request.setAttribute("bodyError", new ExceptionBody(400,"User with such login already exist!"));
        }catch (PersistenceException e){
            request.setAttribute("bodyError", new ExceptionBody(500, "Error with BD"));
        }
    }
}
