package com.example.weatherproject.service;

import com.example.weatherproject.entity.Session;
import com.example.weatherproject.exception.session_exception.SessionNotFoundException;
import com.example.weatherproject.model.UserDto;

import java.util.Date;

public interface SessionService {

    Session create(String sessionId, UserDto userDto, Date date);

    boolean checkSession(String sessionId) throws SessionNotFoundException;
}
