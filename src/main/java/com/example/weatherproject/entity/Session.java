package com.example.weatherproject.entity;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sessions")
public class Session{

    @Id
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false)
    private User user;

    @Column(name = "expiresAt", nullable = false)
    private Date ExpiresAt;

    public Session() {
    }

    public Session(String id, User user, Date expiresAt) {
        this.id = id;
        this.user = user;
        ExpiresAt = expiresAt;
    }

    public Session(User user, Date expiresAt) {
        this.user = user;
        ExpiresAt = expiresAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiresAt() {
        return ExpiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        ExpiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", user=" + user +
                ", ExpiresAt=" + ExpiresAt +
                '}';
    }
}
