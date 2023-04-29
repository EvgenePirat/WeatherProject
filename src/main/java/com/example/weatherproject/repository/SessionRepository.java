package com.example.weatherproject.repository;

import com.example.weatherproject.entity.Session;
import com.example.weatherproject.exception.session_exception.SessionAlreadyExistForUserException;

public interface SessionRepository {

    Session save(Session session);

    boolean checkSession(Long userId);

    Session getSession(Long userId);


}
