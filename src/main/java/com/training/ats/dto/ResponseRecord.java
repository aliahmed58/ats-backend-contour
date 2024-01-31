package com.training.ats.dto;

import jakarta.validation.constraints.NotNull;

public record ResponseRecord(
        @NotNull
        int statusCode,
        @NotNull
        String message
) {
}
