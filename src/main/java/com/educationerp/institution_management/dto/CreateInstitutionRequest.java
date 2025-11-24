package com.educationerp.institution_management.dto;

import com.educationerp.security.enums.TenantType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * DTO for creating a new institution
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
public class CreateInstitutionRequest {

    @NotBlank(message = "Institution name is required")
    @Size(max = 200, message = "Institution name must not exceed 200 characters")
    private String name;

    @NotBlank(message = "Institution code is required")
    @Size(max = 20, message = "Institution code must not exceed 20 characters")
    private String code;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotBlank(message = "Address is required")
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;

    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @Size(max = 100, message = "State must not exceed 100 characters")
    private String state;

    @Size(max = 20, message = "Postal code must not exceed 20 characters")
    private String postalCode;

    @Size(max = 100, message = "Country must not exceed 100 characters")
    private String country;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Size(max = 200, message = "Website URL must not exceed 200 characters")
    private String websiteUrl;

    @NotNull(message = "Tenant type is required")
    private TenantType tenantType;

    @Size(max = 100, message = "Database name must not exceed 100 characters")
    private String databaseName;

    @Size(max = 100, message = "Schema name must not exceed 100 characters")
    private String schemaName;

    @Size(max = 500, message = "Connection string must not exceed 500 characters")
    private String connectionString;

    @Size(max = 500, message = "Logo URL must not exceed 500 characters")
    private String logoUrl;

    @Size(max = 1000, message = "Settings JSON must not exceed 1000 characters")
    private String settingsJson;

    // Constructors
    public CreateInstitutionRequest() {
    }

    public CreateInstitutionRequest(String name, String code, String description, String address, 
                                  String city, String state, String postalCode, String country, 
                                  String phoneNumber, String email, String websiteUrl, TenantType tenantType) {
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
        this.tenantType = tenantType;
    }

    // Getters and Setters
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

    public TenantType getTenantType() {
        return tenantType;
    }

    public void setTenantType(TenantType tenantType) {
        this.tenantType = tenantType;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
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

    // equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateInstitutionRequest that = (CreateInstitutionRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(code, that.code) &&
                Objects.equals(description, that.description) &&
                Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(state, that.state) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(websiteUrl, that.websiteUrl) &&
                tenantType == that.tenantType &&
                Objects.equals(databaseName, that.databaseName) &&
                Objects.equals(schemaName, that.schemaName) &&
                Objects.equals(connectionString, that.connectionString) &&
                Objects.equals(logoUrl, that.logoUrl) &&
                Objects.equals(settingsJson, that.settingsJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, description, address, city, state, postalCode, country, 
                           phoneNumber, email, websiteUrl, tenantType, databaseName, schemaName, 
                           connectionString, logoUrl, settingsJson);
    }

    @Override
    public String toString() {
        return "CreateInstitutionRequest{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", email='" + email + '\'' +
                ", tenantType=" + tenantType +
                '}';
    }
}
