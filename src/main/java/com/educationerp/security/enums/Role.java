package com.educationerp.security.enums;

/**
 * User roles in the Education ERP System
 * Defines the access levels and permissions for different user types
 * 
 * @author Education ERP Team
 * @version 1.0.0
 */
public enum Role {
    SUPER_ADMIN("Super Administrator", "Full system access"),
    INSTITUTION_ADMIN("Institution Administrator", "Institution-level management"),
    BRANCH_MANAGER("Branch Manager", "Branch-level management"),
    COURSE_MANAGER("Course Manager", "Course and curriculum management"),
    ACCOUNTANT("Accountant", "Financial operations and reporting"),
    REPORT_VIEWER("Report Viewer", "Report access only"),
    TEACHER("Teacher", "Teaching and student evaluation"),
    STUDENT("Student", "Student portal access"),
    PARENT("Parent", "Parent portal access");

    private final String displayName;
    private final String description;

    Role(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Check if this role has read-only access
     */
    public boolean isReadOnly() {
        return this == REPORT_VIEWER || this == PARENT;
    }

    @Override
    public String toString() {
        return name();
    }
}

