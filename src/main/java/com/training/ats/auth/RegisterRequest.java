package com.training.ats.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * auth request record that contains the information that will be received in POST from
 * an auth request such as when registering the user
 */

public record RegisterRequest(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        String username,
        @NotNull
        String password,
        @NotNull
        String confirmPassword
) {}


