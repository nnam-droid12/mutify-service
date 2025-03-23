package com.mutify.mutify.business.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "businesses")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String businessName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String businessType;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Business(String id, String businessName, String email, String businessType, String country, LocalDateTime createdAt) {
        this.id = id;
        this.businessName = businessName;
        this.email = email;
        this.businessType = businessType;
        this.country = country;
        this.createdAt = createdAt;
    }

    public Business() {
    }

    public String getId() {
        return this.id;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public String getCountry() {
        return this.country;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
