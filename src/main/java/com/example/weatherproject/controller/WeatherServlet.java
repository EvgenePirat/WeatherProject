package com.example.weatherproject.controller;

import com.example.weatherproject.entity.weather.WeatherInfo;
import com.example.weatherproject.exception.ExceptionBody;
import com.example.weatherproject.model.WeatherInfoDto;
import com.example.weatherproject.model.mapper.WeatherInfoMapper;
import com.example.weatherproject.service.WeatherService;
import com.example.weatherproject.service.impl.WeatherServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


//I need think about how good will make repository with start transaction and commit and rollback

// I need think about

// i need make mapper on level repository, i need change session and auth service

@WebServlet(name = "WeatherServlet", value = "/security/weather")
public class WeatherServlet extends HttpServlet {

    private WeatherService weatherService = new WeatherServiceImpl();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String weatherId = request.getParameter("id");
        System.out.println(weatherId);
        //weatherService.delete(Long.getLong(weatherId));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String nameCity = request.getParameter("city");
            Long userId = (Long) request.getAttribute("idUser");
            WeatherInfo weatherInfo = weatherService.foundAndSave(nameCity, userId);
            WeatherInfoDto weatherInfoDto = WeatherInfoMapper.convertedInDto(weatherInfo);
            request.setAttribute("WeatherInDtoNew",weatherInfoDto);
        }catch (IOException e){
            request.setAttribute("bodyError", new ExceptionBody(400,"Required form field is missing"));
        }

    }

}
