package com.training.ats.responsedto;

import jakarta.validation.constraints.NotNull;

public record ResponseRecord(
        @NotNull
        int statusCode,
        @NotNull
        String message
) {
}
