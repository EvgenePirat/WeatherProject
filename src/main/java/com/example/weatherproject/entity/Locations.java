package com.example.weatherproject.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Locations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,length = 150)
    private String name;

    @Column(name = "Latitude",columnDefinition = "DECIMAL(12,6)", nullable = false)
    private BigDecimal latitude;

    @Column(name = "Longitude",columnDefinition = "DECIMAL(12,6)", nullable = false)
    private BigDecimal longitude;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private User user;

    public Locations() {
    }

    public Locations(String name, BigDecimal latitude, BigDecimal longitude, User user) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", user=" + user +
                '}';
    }
}
