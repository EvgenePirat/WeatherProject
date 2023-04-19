package com.example.weatherproject.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id", unique = true, nullable = false)
    private User user;

    @Column(name = "expiresAt", nullable = false)
    private LocalDateTime ExpiresAt;

    public Session() {
    }

    public Session(User user, LocalDateTime expiresAt) {
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

    public LocalDateTime getExpiresAt() {
        return ExpiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
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
