package com.example.weatherproject.repository.impl;

import com.example.weatherproject.entity.User;
import com.example.weatherproject.exception.LoginAlreadyExistException;
import com.example.weatherproject.repository.AuthRepository;
import com.example.weatherproject.сonfig.FactorySession;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AuthRepositoryImpl implements AuthRepository {

    @Override
    public User save(User user) throws PersistenceException, LoginAlreadyExistException {
        try(Session sessionWithHibernate = FactorySession.getSession()) {
            Transaction transaction = sessionWithHibernate.beginTransaction();
            if(checkExistUser(user.getLogin(), sessionWithHibernate) == true) throw new LoginAlreadyExistException();
            sessionWithHibernate.persist(user);
            transaction.commit();
        }
        return user;
    }
    @Override
    public boolean checkExistUser(String login, Session session) {
        try {
            Query<User> query = session.createQuery("SELECT User FROM User WHERE login = :login", User.class);
            query.setParameter("login", login);
            User user = query.uniqueResult();
            return user != null;
        } catch (NoResultException e) {
            return false;
        }
    }
}
