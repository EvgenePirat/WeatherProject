package com.example.weatherproject.model.mapper;

import com.example.weatherproject.entity.Locations;
import com.example.weatherproject.entity.User;
import com.example.weatherproject.model.LocationsDto;

import java.util.ArrayList;
import java.util.List;

public class LocationMapper {

    public static LocationsDto transformation(Locations locations){
        return new LocationsDto(locations.getId(),locations.getName(),locations.getLatitude(),locations.getLongitude(),locations.getUser().getId());
    }

    public static Locations transformation(LocationsDto locationsDto){
        User user = new User();
        user.setId(locationsDto.getUserId());
        return new Locations(locationsDto.getId(),locationsDto.getName(),locationsDto.getLatitude(),locationsDto.getLongitude(),user);
    }

    public static List<LocationsDto> transformationList(List<Locations> locationsList){
        List<LocationsDto> locationsDtoList = new ArrayList<>();
        for(Locations locations : locationsList){
            locationsDtoList.add(transformation(locations));
        }
        return locationsDtoList;
    }

    public static List<Locations> transformationListDto(List<LocationsDto> locationsListDto){
        List<Locations> locationsList = new ArrayList<>();
        for(LocationsDto locations : locationsListDto){
            locationsList.add(transformation(locations));
        }
        return locationsList;
    }
}
