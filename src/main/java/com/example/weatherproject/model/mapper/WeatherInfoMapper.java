package com.example.weatherproject.model.mapper;

import com.example.weatherproject.entity.weather.*;
import com.example.weatherproject.model.WeatherInfoDto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WeatherInfoMapper {

    public static Set<WeatherInfoDto> convertedSet(Set<WeatherInfo> weatherInfoSet){
        Set<WeatherInfoDto> weatherInfoDtoSet = new HashSet<>();
        for(WeatherInfo weatherInfo : weatherInfoSet){
            weatherInfoDtoSet.add(convertedInDto(weatherInfo));
        }
        return weatherInfoDtoSet;
    }

    public static WeatherInfoDto convertedInDto(WeatherInfo weatherInfo){
        return new WeatherInfoDto(weatherInfo.getId(), weatherInfo.getName(), weatherInfo.getTimezone(), weatherInfo.getCoord().getLon(),weatherInfo.getCoord().getLat(),weatherInfo.getMain().getTemp(),weatherInfo.getMain().getFeels_like(),weatherInfo.getWind().getSpeed(),weatherInfo.getWeather().get(0).getDescription(),weatherInfo.getVisibility());
    }

    public static WeatherInfo convertedInEntity(WeatherInfoDto weatherInfoDto){
        Weather weather = new Weather();
        weather.setDescription(weatherInfoDto.getDescription());
        Coord coord = new Coord(weatherInfoDto.getLon(), weatherInfoDto.getLat());
        Main main = new Main();
        main.setTemp(weatherInfoDto.getTemp());
        main.setFeels_like(weatherInfoDto.getFeels_like());
        Wind wind = new Wind();
        wind.setSpeed(weatherInfoDto.getSpeed());
        return new WeatherInfo(weatherInfoDto.getId(), weatherInfoDto.getName(),weatherInfoDto.getTimezone(), Collections.singletonList(weather), coord, main,wind, weatherInfoDto.getVisibility());
    }
}
