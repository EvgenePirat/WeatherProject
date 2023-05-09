package com.example.weatherproject.model;

public class WeatherInfoDto {

    private int id;

    private String name;

    private int timezone;

    private double lon;

    private double lat;

    private double temp;

    private double feels_like;

    private double speed;

    private String description;

    private int visibility;

    public WeatherInfoDto() {
    }

    public WeatherInfoDto(int id, String name, int timezone, double lon, double lat, double temp, double feels_like, double speed, String description, int visibility) {
        this.id = id;
        this.name = name;
        this.timezone = timezone;
        this.lon = lon;
        this.lat = lat;
        this.temp = temp;
        this.feels_like = feels_like;
        this.speed = speed;
        this.description = description;
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

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "WeatherInfoDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timezone=" + timezone +
                ", lon=" + lon +
                ", lat=" + lat +
                ", temp=" + temp +
                ", feels_like=" + feels_like +
                ", speed=" + speed +
                ", description='" + description + '\'' +
                ", visibility=" + visibility +
                '}';
    }
}
