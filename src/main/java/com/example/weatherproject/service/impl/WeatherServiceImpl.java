package com.example.weatherproject.service.impl;

import com.example.weatherproject.entity.Locations;
import com.example.weatherproject.entity.User;
import com.example.weatherproject.entity.weather.WeatherInfo;
import com.example.weatherproject.model.LocationsDto;
import com.example.weatherproject.repository.WeatherRepository;
import com.example.weatherproject.repository.impl.WeatherRepositoryImpl;
import com.example.weatherproject.service.WeatherService;
import com.example.weatherproject.service.helper.OpenWeather;
import com.example.weatherproject.service.helper.impl.OpenWeatherImpl;

import java.io.IOException;
import java.math.BigDecimal;

public class WeatherServiceImpl implements WeatherService {

    private WeatherRepository weatherRepository = new WeatherRepositoryImpl();

    private OpenWeather openWeather = new OpenWeatherImpl();

    @Override
    public LocationsDto foundAndSave(String city, Long userId) throws IOException {
        WeatherInfo weatherInfo = openWeather.searchWeatherWithCity(city);
        User user = new User();
        user.setId(userId);
        weatherRepository.save(new Locations(weatherInfo.getName(),BigDecimal.valueOf(weatherInfo.getCoord().getLat()), BigDecimal.valueOf(weatherInfo.getCoord().getLon()),user));
        return null;
    }
}
