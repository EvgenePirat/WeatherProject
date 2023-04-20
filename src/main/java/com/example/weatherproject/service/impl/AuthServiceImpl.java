package com.example.weatherproject.service.impl;

import com.example.weatherproject.entity.User;
import com.example.weatherproject.exception.LoginAlreadyExistException;
import com.example.weatherproject.repository.AuthRepository;
import com.example.weatherproject.repository.impl.AuthRepositoryImpl;
import com.example.weatherproject.service.AuthService;
import jakarta.persistence.PersistenceException;
import org.mindrot.jbcrypt.BCrypt;

public class AuthServiceImpl implements AuthService {

    private AuthRepository authRepository = new AuthRepositoryImpl();

    @Override
    public User registration(User user) throws LoginAlreadyExistException, PersistenceException {
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        return authRepository.save(user);
    }

    @Override
    public User login(User user) {
        return null;
    }
}
