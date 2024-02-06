package com.training.ats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * record to send applicant data as a response entity
 */
public record AtsUserRecord(
        @NotNull(message = "First name cannot be null")
        @NotBlank(message = "First name cannot be blank")
        String firstname,
        @NotNull(message = "Last name cannot be null")
        String lastname,

        @NotNull(message = "username cannot be null")
        @NotBlank(message = "username cannot be blank")
        String username
) {
}
