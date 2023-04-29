package com.example.weatherproject.repository.impl;

import com.example.weatherproject.entity.Locations;
import com.example.weatherproject.repository.WeatherRepository;
import com.example.weatherproject.сonfig.FactorySession;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class WeatherRepositoryImpl implements WeatherRepository {
    @Override
    public Locations save(Locations locations) {
        try(Session session = FactorySession.getSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(locations);
            transaction.commit();
            return locations;
        }
    }
}
