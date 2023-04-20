package com.example.weatherproject.service;

import com.example.weatherproject.entity.User;
import com.example.weatherproject.exception.LoginAlreadyExistException;
import jakarta.persistence.PersistenceException;

public interface AuthService {

    User registration(User user) throws LoginAlreadyExistException, PersistenceException;

    User login(User user);
}
