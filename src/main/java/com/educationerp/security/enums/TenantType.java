package com.educationerp.security.enums;

/**
 * Tenant deployment type in multi-tenant architecture
 * Defines how data is isolated for different institutions
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
public enum TenantType {
    SHARED_SCHEMA("Shared Schema", "All tenants share the same database schema"),
    DEDICATED_DB("Dedicated Database", "Each tenant has its own database"),
    ON_PREMISE("On-Premise", "Self-hosted deployment with local infrastructure");

    private final String displayName;
    private final String description;

    TenantType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name();
    }
}

