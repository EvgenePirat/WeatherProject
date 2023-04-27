package com.example.weatherproject.repository.impl;

import com.example.weatherproject.entity.Session;
import com.example.weatherproject.entity.User;
import com.example.weatherproject.exception.session_exception.SessionAlreadyExistForUserException;
import com.example.weatherproject.repository.SessionRepository;
import com.example.weatherproject.сonfig.FactorySession;
import jakarta.persistence.NoResultException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class SessionRepositoryImpl implements SessionRepository {
    @Override
    public Session save(Session sessionUser) throws SessionAlreadyExistForUserException {
        try(org.hibernate.Session sessionHibernate = FactorySession.getSession()) {
            Transaction transaction = sessionHibernate.beginTransaction();
            if(checkSession(sessionUser.getUser().getId(), sessionHibernate) == true) throw new SessionAlreadyExistForUserException();
            sessionHibernate.persist(sessionUser);
            transaction.commit();
        }
        return sessionUser;
    }

    @Override
    public boolean checkSession(Long userId, org.hibernate.Session sessionHibernate) {
        try {
            Query<Session> query = sessionHibernate.createQuery("FROM Session WHERE user.id = :id", Session.class);
            query.setParameter("id", userId);
            Session session = query.uniqueResult();
            return session != null;
        } catch (NoResultException e) {
            return false;
        }
    }


}
