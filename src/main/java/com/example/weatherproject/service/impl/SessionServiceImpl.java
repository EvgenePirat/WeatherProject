package com.example.weatherproject.service.impl;

import com.example.weatherproject.entity.Session;
import com.example.weatherproject.model.UserDto;
import com.example.weatherproject.model.mapper.UserMapper;
import com.example.weatherproject.repository.SessionRepository;
import com.example.weatherproject.repository.impl.SessionRepositoryImpl;
import com.example.weatherproject.service.SessionService;

import java.util.Date;

public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository = new SessionRepositoryImpl();

    @Override
    public Session create(String sessionId, UserDto userDto, Date date){
        Session session = new Session(sessionId, UserMapper.transformation(userDto),date);
        return sessionRepository.save(session);
    }
}
