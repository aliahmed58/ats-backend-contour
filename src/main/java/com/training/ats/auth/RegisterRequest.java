package com.training.ats.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * auth request record that contains the information that will be received in POST from
 * an auth request such as when registering the user
 */

public record RegisterRequest(
        @NotNull(message = "first name cannot be null")
        @NotBlank(message = "first name cannot be blank")
        String firstName,
        @NotNull(message = "last name cannot be null")
        @NotBlank(message = "last name cannot be blank")
        String lastName,
        @NotNull(message = "username cannot be null")
        @NotBlank(message = "username cannot be blank")
        String username,
        @NotNull(message = "password cannot be null")
        @NotBlank(message = "password cannot be blank")
        String password,
        @NotNull(message = "confirm password cannot be null")
        @NotBlank(message = "confirm password cannot be blank")
        String confirmPassword
) {
}


