package com.example.weatherproject.repository;

import com.example.weatherproject.entity.Session;
import com.example.weatherproject.exception.session_exception.SessionNotFoundException;

import java.util.Optional;

public interface SessionRepository {

    Session save(Session session);

    Optional<Session> getSession(Long userId);

    Optional<Session> getSession(String sessionId);

    boolean checkSession(String sessionId) throws SessionNotFoundException;

}
