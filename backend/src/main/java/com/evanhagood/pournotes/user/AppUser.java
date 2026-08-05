package com.evanhagood.pournotes.user;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "app_users",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_app_users_google_subject",
            columnNames = "google_subject"
        )
    }
)
public class AppUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
        name = "subject",
        nullable = false,
        updatable = false,
        length = 255
    )
    private String subject;

    @Column(name = "display_name", nullable = false, length = 100)
    private String displayName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private UserRole role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected AppUser() {}

    public AppUser(String googleSubject, String displayName) {
        if (googleSubject == null || googleSubject.isBlank()) {
            throw new IllegalArgumentException("Google subject is required");
        }

        if (displayName == null || displayName.isBlank()) {
            throw new IllegalArgumentException("Display name is required");
        }

        this.subject = googleSubject;
        this.displayName = displayName;
        this.role = UserRole.USER;
    }

    @PrePersist
    private void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserRole getRole() {
        return role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void updateDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void changeRole(UserRole role) {
        this.role = role;
    }
}
