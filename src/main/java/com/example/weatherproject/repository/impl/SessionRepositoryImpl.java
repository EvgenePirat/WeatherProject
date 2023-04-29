package com.example.weatherproject.repository.impl;

import com.example.weatherproject.entity.Session;
import com.example.weatherproject.exception.session_exception.SessionNotFoundException;
import com.example.weatherproject.repository.SessionRepository;
import com.example.weatherproject.сonfig.FactorySession;
import jakarta.persistence.NoResultException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.Optional;

public class SessionRepositoryImpl implements SessionRepository {

    @Override
    public Session save(Session sessionUser) {
        try(org.hibernate.Session sessionHibernate = FactorySession.getSession()) {
            Transaction transaction = sessionHibernate.beginTransaction();
            Optional<Session> sessionOld = getSession(sessionUser.getUser().getId());
            if(sessionOld.isPresent() == true){
                sessionHibernate.delete(sessionOld.get());
                sessionHibernate.flush();
            }
            sessionHibernate.persist(sessionUser);
            transaction.commit();
        }
        return sessionUser;
    }

    @Override
    public Optional<Session> getSession(Long userId) {
        org.hibernate.Session sessionHibernate = FactorySession.getSession();
        if(sessionHibernate.isConnected()){
            return queryForGetSessionWithUserId(userId,sessionHibernate);
        }else{
            Transaction transaction = sessionHibernate.beginTransaction();
            Optional<Session> session = queryForGetSessionWithUserId(userId,sessionHibernate);
            transaction.commit();
            return session;
        }
    }

    @Override
    public Optional<Session> getSession(String sessionId) {
        org.hibernate.Session sessionHibernate = FactorySession.getSession();
        Transaction tx = sessionHibernate.getTransaction();
        if(tx.isActive()){
            return processGetSession(sessionId,sessionHibernate);
        }else{
            tx.begin();
            Optional<Session> session = processGetSession(sessionId, sessionHibernate);
            tx.commit();
            return session;
        }
    }

    private Optional<Session> processGetSession(String sessionId, org.hibernate.Session sessionHibernate){
        if(sessionHibernate.isConnected()){
            return queryForGetSessionWithSessionId(sessionId,sessionHibernate);
        }else{
            Transaction transaction = sessionHibernate.beginTransaction();
            Optional<Session> session = queryForGetSessionWithSessionId(sessionId,sessionHibernate);
            transaction.commit();
            return session;
        }
    }

    @Override
    public boolean checkSession(String sessionId) throws SessionNotFoundException {
        Optional<Session> session = getSession(sessionId);
        if(session.isPresent() == false) throw new SessionNotFoundException();
        return session.get().getExpiresAt().after(new Date());
    }

    private Optional<Session> queryForGetSessionWithSessionId(String sessionId, org.hibernate.Session sessionHibernate){
        Query<Session> query = sessionHibernate.createQuery("FROM Session WHERE id = :id", Session.class);
        query.setParameter("id", sessionId);
        Session session = query.uniqueResult();
        return Optional.of(session);
    }

    private Optional<Session> queryForGetSessionWithUserId(Long userId, org.hibernate.Session sessionHibernate){
        Query<Session> query = sessionHibernate.createQuery("FROM Session WHERE user.id = :id", Session.class);
        query.setParameter("id", userId);
        Session session = query.uniqueResult();
        return Optional.of(session);
    }

}
