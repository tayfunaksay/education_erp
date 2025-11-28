package com.educationerp.institution_management.entity;

import com.educationerp.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Branch entity for the Education ERP System
 * Represents branches of educational institutions
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
@Entity
@Table(name = "branches", 
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"institution_id", "code"})
       })
public class Branch extends BaseEntity {

    @NotNull(message = "Institution ID is required")
    @Column(name = "institution_id", nullable = false)
    private Long institutionId;

    @NotBlank(message = "Branch name is required")
    @Size(max = 200, message = "Branch name must not exceed 200 characters")
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @NotBlank(message = "Branch code is required")
    @Size(max = 20, message = "Branch code must not exceed 20 characters")
    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "description", length = 500)
    private String description;

    @NotBlank(message = "Address is required")
    @Size(max = 500, message = "Address must not exceed 500 characters")
    @Column(name = "address", nullable = false, length = 500)
    private String address;

    @Size(max = 100, message = "City must not exceed 100 characters")
    @Column(name = "city", length = 100)
    private String city;

    @Size(max = 100, message = "State must not exceed 100 characters")
    @Column(name = "state", length = 100)
    private String state;

    @Size(max = 20, message = "Postal code must not exceed 20 characters")
    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Size(max = 100, message = "Country must not exceed 100 characters")
    @Column(name = "country", length = 100)
    private String country;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 200, message = "Website URL must not exceed 200 characters")
    @Column(name = "website_url", length = 200)
    private String websiteUrl;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Size(max = 500, message = "Logo URL must not exceed 500 characters")
    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Size(max = 1000, message = "Settings JSON must not exceed 1000 characters")
    @Column(name = "settings_json", length = 1000)
    private String settingsJson;

    // Constructors
    public Branch() {
    }

    public Branch(Long institutionId, String name, String code, String description, 
                  String address, String city, String state, String postalCode, 
                  String country, String phoneNumber, String email, String websiteUrl) {
        this.institutionId = institutionId;
        this.name = name;
        this.code = code;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.websiteUrl = websiteUrl;
    }

    // Getters and Setters
    public Long getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Long institutionId) {
        this.institutionId = institutionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getSettingsJson() {
        return settingsJson;
    }

    public void setSettingsJson(String settingsJson) {
        this.settingsJson = settingsJson;
    }

    // Business methods
    public String getFullAddress() {
        StringBuilder address = new StringBuilder();
        if (this.address != null) address.append(this.address);
        if (this.city != null) {
            if (address.length() > 0) address.append(", ");
            address.append(this.city);
        }
        if (this.state != null) {
            if (address.length() > 0) address.append(", ");
            address.append(this.state);
        }
        if (this.postalCode != null) {
            if (address.length() > 0) address.append(" ");
            address.append(this.postalCode);
        }
        if (this.country != null) {
            if (address.length() > 0) address.append(", ");
            address.append(this.country);
        }
        return address.toString();
    }

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Branch branch = (Branch) o;
        return Objects.equals(institutionId, branch.institutionId) &&
                Objects.equals(code, branch.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), institutionId, code);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + getId() +
                ", institutionId=" + institutionId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + getIsActive() +
                '}';
    }
}
