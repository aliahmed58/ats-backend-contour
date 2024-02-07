package com.training.ats.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * record to send a user's basic data as a response
 *
 * @param jwtToken
 */
public record AuthResponse(
        @NotNull(message = "jwt token cannot be null")
        @NotBlank(message = "jwt token cannot be blank")
        String jwtToken
) {
}
