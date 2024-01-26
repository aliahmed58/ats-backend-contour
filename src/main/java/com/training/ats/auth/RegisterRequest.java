package com.training.ats.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * auth request record that contains the information that will be received in POST from
 * an auth request such as when registering the user
 */

public record RegisterRequest(
        String username,
        String password,
        String confirmPassword
) {}


