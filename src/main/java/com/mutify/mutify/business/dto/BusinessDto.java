package com.mutify.mutify.business.dto;


public class BusinessDto {
    private String businessName;
    private String email;
    private String businessType;
    private String country;

    public BusinessDto(String businessName, String email, String businessType, String country) {
        this.businessName = businessName;
        this.email = email;
        this.businessType = businessType;
        this.country = country;
    }

    public BusinessDto() {
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
}
