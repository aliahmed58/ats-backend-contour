package com.training.ats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LevelRecord(
        @NotNull(message = "Level cannot be null")
        @NotBlank(message = "Level cannot be blank")
        String level
) {
}
