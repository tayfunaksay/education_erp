package com.educationerp.security.tenant;

/**
 * Thread-local tenant context for multi-tenant support
 * Stores current tenant information for the request thread
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
public class TenantContext {

    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    /**
     * Set current tenant for the thread
     */
    public static void setCurrentTenant(String tenantId) {
        currentTenant.set(tenantId);
    }

    /**
     * Get current tenant for the thread
     */
    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    /**
     * Clear tenant context for the thread
     */
    public static void clear() {
        currentTenant.remove();
    }

    /**
     * Check if tenant context is set
     */
    public static boolean hasTenant() {
        return currentTenant.get() != null;
    }
}
