package com.example.weatherproject.service;

import com.example.weatherproject.entity.Locations;
import com.example.weatherproject.entity.weather.WeatherInfo;
import com.example.weatherproject.model.LocationsDto;
import com.example.weatherproject.model.WeatherInfoDto;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface WeatherService {

    WeatherInfo foundAndSave(String city, Long userId) throws IOException;

    Set<WeatherInfo> getAll (Long userId) throws IOException;

    void delete(Long weatherId);

}
