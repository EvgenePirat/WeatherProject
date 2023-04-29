package com.example.weatherproject.repository.impl;

import com.example.weatherproject.entity.Session;
import com.example.weatherproject.repository.SessionRepository;
import com.example.weatherproject.сonfig.FactorySession;
import jakarta.persistence.NoResultException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;

public class SessionRepositoryImpl implements SessionRepository {

    @Override
    public Session save(Session sessionUser) {
        try(org.hibernate.Session sessionHibernate = FactorySession.getSession()) {
            Transaction transaction = sessionHibernate.beginTransaction();
            if(checkSession(sessionUser.getUser().getId()) == true){
                return getSession(sessionUser.getUser().getId());
            }else{
                sessionHibernate.persist(sessionUser);
                transaction.commit();
            }
        }
        return sessionUser;
    }

    @Override
    public Session getSession(Long userId) {
        org.hibernate.Session sessionHibernate = FactorySession.getSession();
        if(sessionHibernate.isConnected()){
            return queryForGetSession(userId,sessionHibernate);
        }else{
            Transaction transaction = sessionHibernate.beginTransaction();
            Session session = queryForGetSession(userId,sessionHibernate);
            transaction.commit();
            return session;
        }
    }

    private Session queryForGetSession(Long userId, org.hibernate.Session sessionHibernate){
        Query<Session> query = sessionHibernate.createQuery("FROM Session WHERE user.id = :id", Session.class);
        query.setParameter("id", userId);
        Session session = query.uniqueResult();
        return session;
    }


    @Override
    public boolean checkSession(Long userId) {
        org.hibernate.Session sessionHibernate = FactorySession.getSession();
        if(sessionHibernate.isConnected()){
            return existSession(userId,sessionHibernate);
        }else{
            Transaction transaction = sessionHibernate.beginTransaction();
            boolean resultExist = existSession(userId,sessionHibernate);
            transaction.commit();
            return resultExist;
        }
    }

    private boolean existSession(Long userId, org.hibernate.Session sessionHibernate){
        try {
            Session session = getSession(userId);
            if(session == null)return false;
            System.out.println(session.getExpiresAt().before(new Date()));
            if(session.getExpiresAt().before(new Date())){
                sessionHibernate.delete(session);
                sessionHibernate.flush();
                return false;
            }
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

}
