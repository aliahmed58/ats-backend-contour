package com.training.ats.exceptions;

public enum ErrorType {
    ENTITY_NOT_FOUND("Entity not found"),
    UNAUTHORIZED_OPERATION("Unauthorized operation"),
    ENTITY_UPDATED("Entity updated"),
    ENTITY_DELETED("Entity deleted"),
    PASS_DO_NOT_MATCH("Passwords do not match"),
    ENTITY_SAVED("Entity saved"),
    ENTITY_EXISTS("Entity already exists"),
    ENTITY_DELETED_ALL("All entities deleted");

    public final String error;

    private ErrorType(String error) {
        this.error = error;
    }
}
