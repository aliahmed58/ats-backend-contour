package com.training.ats.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * record to send a jwt token to the user as a json object on successful authentication
 *
 * @param jwtToken
 */
public record AuthResponse(
        @NotNull(message = "jwt token cannot be null")
        @NotBlank(message = "jwt token cannot be blank")
        String jwtToken
) {
}
