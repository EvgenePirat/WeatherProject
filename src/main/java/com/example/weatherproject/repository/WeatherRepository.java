package com.example.weatherproject.repository;

import com.example.weatherproject.entity.Locations;

public interface WeatherRepository {

    Locations save(Locations locations);
}
