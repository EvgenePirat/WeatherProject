package com.example.weatherproject.service;

import com.example.weatherproject.exception.auth_exception.LoginAlreadyExistException;
import com.example.weatherproject.exception.auth_exception.PasswordWrongException;
import com.example.weatherproject.exception.user_exception.UserNotFoundException;
import com.example.weatherproject.model.UserDto;
import jakarta.persistence.PersistenceException;

public interface AuthService {

    UserDto registration(UserDto user) throws LoginAlreadyExistException, PersistenceException;

    UserDto login(UserDto user) throws UserNotFoundException, PasswordWrongException;
}
