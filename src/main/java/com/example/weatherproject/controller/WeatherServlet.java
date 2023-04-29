package com.example.weatherproject.controller;

import com.example.weatherproject.service.WeatherService;
import com.example.weatherproject.service.impl.WeatherServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "WeatherServlet", value = "/security/weather")
public class WeatherServlet extends HttpServlet {

    private WeatherService weatherService = new WeatherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameCity = request.getParameter("city");
        System.out.println(request.getParameter("userId"));
        Long userId = Long.valueOf(request.getParameter("userId"));
        weatherService.foundAndSave(nameCity, userId);
    }

}
