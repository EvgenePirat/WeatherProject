package com.example.weatherproject.repository;

import com.example.weatherproject.entity.Session;
import com.example.weatherproject.exception.session_exception.SessionAlreadyExistForUserException;

public interface SessionRepository {

    Session save(Session session) throws SessionAlreadyExistForUserException;

    boolean checkSession(Long userId, org.hibernate.Session sessionHibernate) throws SessionAlreadyExistForUserException;

}
