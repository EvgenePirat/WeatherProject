package com.example.weatherproject.repository;

import com.example.weatherproject.entity.Locations;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface WeatherRepository {

    Optional<Locations> save(Locations locations, Long userId);

    boolean checkLocation(Long userId, String name);

    Optional<Set<Locations>> getAll(Long userId);

    void delete(Long weatherId);

}
