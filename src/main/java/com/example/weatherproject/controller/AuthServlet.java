package com.example.weatherproject.controller;

import com.example.weatherproject.exception.*;
import com.example.weatherproject.exception.auth_exception.LoginAlreadyExistException;
import com.example.weatherproject.exception.auth_exception.PasswordWrongException;
import com.example.weatherproject.exception.session_exception.SessionAlreadyExistForUserException;
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
import java.time.LocalDateTime;
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
            HttpSession session = (HttpSession) request.getAttribute("session");
            Date date = new Date(System.currentTimeMillis()+(session.getMaxInactiveInterval() * 1000));
            System.out.println(date);
            sessionService.create(session.getId(),userAfterCheck,date);
            request.setAttribute("user",userAfterCheck);
        }catch (PersistenceException e){
            e.printStackTrace();
            response = getReadyResponse(500, "Error with BD",response);
            request.setAttribute("error",true);
        } catch (UserNotFoundException e) {
            response = getReadyResponse(400, "Bad request, login not found!", response);
            request.setAttribute("error",true);
        } catch (PasswordWrongException e) {
            response = getReadyResponse(400, "Password is wrong!", response);
            request.setAttribute("error",true);
        } catch (SessionAlreadyExistForUserException e) {
            response = getReadyResponse(400, "Session already exist!", response);
            request.setAttribute("error",true);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try{
            UserDto user = gson.fromJson(request.getReader(), UserDto.class);
            UserDto userAfterSave = authService.registration(user);
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
