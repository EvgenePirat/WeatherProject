package com.example.weatherproject.repository.impl;

import com.example.weatherproject.entity.Locations;
import com.example.weatherproject.repository.WeatherRepository;
import com.example.weatherproject.сonfig.FactorySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class WeatherRepositoryImpl implements WeatherRepository {
    @Override
    public Optional<Locations> save(Locations locations, Long userId) {
        try(Session session = FactorySession.getSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(locations);
            transaction.commit();
            return Optional.of(locations);
        }
    }

    @Override
    public boolean checkLocation(Long userId, String name) {
        org.hibernate.Session sessionHibernate = FactorySession.getSession();
        Transaction transaction = sessionHibernate.getTransaction();
        if(transaction.isActive()){
            return processCheckLocation(userId,name,sessionHibernate);
        }else{
            transaction.begin();
            boolean result = processCheckLocation(userId,name,sessionHibernate);
            transaction.commit();
            return result;
        }
    }

    @Override
    public Optional<Set<Locations>> getAll(Long userId) {
        org.hibernate.Session sessionHibernate = FactorySession.getSession();
        Transaction transaction = sessionHibernate.getTransaction();
        if(transaction.isActive()){
            return Optional.of(processFoundLocationSet(userId,sessionHibernate));
        }else{
            transaction.begin();
            Set<Locations> locationsSet = processFoundLocationSet(userId,sessionHibernate);
            transaction.commit();
            return Optional.of(locationsSet);
        }
    }

    @Override
    public void delete(Long weatherId) {
        org.hibernate.Session sessionHibernate = FactorySession.getSession();
        Transaction transaction = sessionHibernate.getTransaction();
        if(transaction.isActive()){
            processDeleteLocation(weatherId,sessionHibernate);
        }else{
            transaction.begin();
            processDeleteLocation(weatherId,sessionHibernate);
            transaction.commit();
        }
    }

    private int processDeleteLocation(Long weatherId, Session sessionHibernate){
        Query query = sessionHibernate.createQuery("DELETE FROM Locations WHERE id = :id");
        query.setParameter("id", weatherId);
        return query.executeUpdate();
    }

    private Set<Locations> processFoundLocationSet(Long userId, Session sessionHibernate){
        Query<Locations> query = sessionHibernate.createQuery("FROM Locations WHERE user.id = :id", Locations.class);
        query.setParameter("id",userId);
        return new HashSet<>(query.getResultList());
    }

    private boolean processCheckLocation(Long userId, String name, Session sessionHibernate){
        Query<String> query = sessionHibernate.createQuery("SELECT name FROM Locations WHERE user.id = :id AND name = :name", String.class);
        query.setParameter("id", userId);
        query.setParameter("name",name);
        String nameForCheck = query.uniqueResult();
        return nameForCheck == null ? false : true;
    }
}
