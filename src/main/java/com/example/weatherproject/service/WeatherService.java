package com.example.weatherproject.service;

import com.example.weatherproject.model.LocationsDto;

import java.io.IOException;

public interface WeatherService {

    LocationsDto foundAndSave(String city, Long userId) throws IOException;

}
