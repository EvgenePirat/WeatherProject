package com.example.weatherproject.entity.weather;

import java.util.List;

public class WeatherInfo {

    private Coord coord;

    private List<Weather> weather;

    private int id;

    private String name;

    private int timezone;

    private Main main;

    private Wind wind;

    private int visibility;

    public WeatherInfo() {
    }

    public WeatherInfo(int id, String name, int timezone, List<Weather> weather, Coord coord, Main main, Wind wind, int visibility) {
        this.id = id;
        this.name = name;
        this.timezone = timezone;
        this.weather = weather;
        this.coord = coord;
        this.main = main;
        this.wind = wind;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "coord=" + coord +
                ", weather=" + weather +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", timezone=" + timezone +
                ", main=" + main +
                ", wind=" + wind +
                ", visibility=" + visibility +
                '}';
    }
}
