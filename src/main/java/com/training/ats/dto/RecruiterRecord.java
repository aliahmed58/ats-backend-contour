package com.training.ats.dto;

/**
 * record object to return a recruiters data as a response entity
 */
public record RecruiterRecord(
        String firstName,
        String lastName,
        String username
) {
}
