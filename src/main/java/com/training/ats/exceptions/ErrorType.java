package com.training.ats.exceptions;

public enum ErrorType {
    ENTITY_NOT_FOUND("Entity not found"),
    UNAUTHORIZED_OPERATION("Unauthorized operation"),
    ENTITY_UPDATED("Entity updated"),
    ENTITY_DELETED("Entity deleted"),
    ENTITY_DELETED_ALL("All entities deleted");

    public final String error;

    private ErrorType(String error) {
        this.error = error;
    }
}
