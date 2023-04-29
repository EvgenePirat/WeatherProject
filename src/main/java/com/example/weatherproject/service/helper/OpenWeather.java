package com.example.weatherproject.service.helper;

import com.example.weatherproject.entity.weather.WeatherInfo;

import java.io.IOException;

public interface OpenWeather {

    WeatherInfo searchWeatherWithCity(String city) throws IOException;
}
