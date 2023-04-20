package com.example.weatherproject.repository;

import com.example.weatherproject.entity.User;
import com.example.weatherproject.exception.LoginAlreadyExistException;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;

public interface AuthRepository {

    User save(User user) throws LoginAlreadyExistException, PersistenceException;

    boolean checkExistUser(String login, Session session);
}
