package com.training.ats.auth;

import jakarta.validation.constraints.NotNull;

/**
 * record to store data when an already registered user makes an authentication request
 * @param username
 * @param password
 */
public record AuthRequest(
        @NotNull
        String username,
        @NotNull
        String password
) {}
