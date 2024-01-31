package com.training.ats.models;

import javax.management.relation.Role;

/**
 * end user role types to check for authorization
 */
public enum RoleType {
    APPLICANT("APPLICANT"),
    RECRUITER("RECRUITER");

    public final String role;
    
    private RoleType(String role) {
        this.role = role;
    }
}
