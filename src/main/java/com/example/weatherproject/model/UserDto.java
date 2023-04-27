package com.example.weatherproject.model;

import com.example.weatherproject.entity.Locations;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    @Expose
    private Long id;

    @Expose
    private String login;

    @Expose
    private String password;

    @Expose
    List<LocationsDto> locationsList = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(Long id, String login, String password, List<LocationsDto> locationsList) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.locationsList = locationsList;
    }

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<LocationsDto> getLocationsList() {
        return locationsList;
    }

    public void setLocationsList(List<LocationsDto> locationsList) {
        this.locationsList = locationsList;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", locationsList=" + locationsList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;

        UserDto userDto = (UserDto) o;

        if (!getId().equals(userDto.getId())) return false;
        return getPassword() != null ? getPassword().equals(userDto.getPassword()) : userDto.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }
}
