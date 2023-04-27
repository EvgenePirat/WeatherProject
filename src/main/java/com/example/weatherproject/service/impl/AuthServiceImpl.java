package com.example.weatherproject.service.impl;

import com.example.weatherproject.entity.User;
import com.example.weatherproject.exception.auth_exception.LoginAlreadyExistException;
import com.example.weatherproject.exception.auth_exception.PasswordWrongException;
import com.example.weatherproject.exception.user_exception.UserNotFoundException;
import com.example.weatherproject.model.UserDto;
import com.example.weatherproject.model.mapper.UserMapper;
import com.example.weatherproject.repository.AuthRepository;
import com.example.weatherproject.repository.impl.AuthRepositoryImpl;
import com.example.weatherproject.service.AuthService;
import jakarta.persistence.PersistenceException;
import org.mindrot.jbcrypt.BCrypt;

public class AuthServiceImpl implements AuthService {

    private AuthRepository authRepository = new AuthRepositoryImpl();

    @Override
    public UserDto registration(UserDto user) throws LoginAlreadyExistException, PersistenceException {
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12)));
        return UserMapper.transformation(authRepository.save(UserMapper.transformation(user)));
    }

    @Override
    public UserDto login(UserDto userDto) throws UserNotFoundException, PasswordWrongException {
        return UserMapper.transformation(authRepository.authorization(UserMapper.transformation(userDto)));
    }
}
