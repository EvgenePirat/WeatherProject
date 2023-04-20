package com.example.weatherproject.сonfig;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;

public class FactorySession implements ServletContextListener {

    private static SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) entityManagerFactory.unwrap(SessionFactory.class);
        sessionFactory = sessionFactoryImpl;
    }

    public static Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
