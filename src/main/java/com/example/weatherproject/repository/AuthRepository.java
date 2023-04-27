package com.example.weatherproject.repository;

import com.example.weatherproject.entity.User;
import com.example.weatherproject.exception.auth_exception.LoginAlreadyExistException;
import com.example.weatherproject.exception.auth_exception.PasswordWrongException;
import com.example.weatherproject.exception.user_exception.UserNotFoundException;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;

public interface AuthRepository {

    User save(User user) throws LoginAlreadyExistException, PersistenceException;

    boolean checkExistUser(String login, Session session);

    User authorization(User user) throws UserNotFoundException, PasswordWrongException;
}
