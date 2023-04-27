package com.example.weatherproject.model.mapper;

import com.example.weatherproject.entity.User;
import com.example.weatherproject.model.UserDto;

public class UserMapper {

    public static UserDto transformation(User user){
        return new UserDto(user.getId(),user.getLogin(),user.getPassword(),LocationMapper.transformationList(user.getLocationsList()));
    }

    public static User transformation(UserDto userDto){
        return new  User(userDto.getId(),userDto.getLogin(), userDto.getPassword(), LocationMapper.transformationListDto(userDto.getLocationsList()));
    }
}
