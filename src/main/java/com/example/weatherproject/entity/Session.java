package com.example.weatherproject.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false)
    private User user;

    @Column(name = "expiresAt", nullable = false)
    private Date ExpiresAt;

    public Session() {
    }

    public Session(User user, Date expiresAt) {
        this.user = user;
        ExpiresAt = expiresAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
