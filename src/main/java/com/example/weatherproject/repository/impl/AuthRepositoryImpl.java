package com.example.weatherproject.repository.impl;

import com.example.weatherproject.entity.User;
import com.example.weatherproject.exception.auth_exception.LoginAlreadyExistException;
import com.example.weatherproject.exception.auth_exception.PasswordWrongException;
import com.example.weatherproject.exception.user_exception.UserNotFoundException;
import com.example.weatherproject.repository.AuthRepository;
import com.example.weatherproject.сonfig.FactorySession;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

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
            Query<User> query = session.createQuery("FROM User WHERE login = :login", User.class);
            query.setParameter("login", login);
            User user = query.uniqueResult();
            return user != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public User authorization(User user) throws UserNotFoundException, PasswordWrongException {
        try(Session session = FactorySession.getSession()){
            Transaction transaction = session.beginTransaction();
            if(checkExistUser(user.getLogin(),session) == false) throw new UserNotFoundException();
            Query<User> query = session.createQuery("FROM User WHERE login = :login", User.class);
            query.setParameter("login",user.getLogin());
            User userFromBd = query.uniqueResult();
            if(BCrypt.checkpw(user.getPassword(),userFromBd.getPassword())){
                transaction.commit();
                return userFromBd;
            }else {
                throw new PasswordWrongException();
            }
        }
    }
}
