package com.training.ats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobTypeRecord(

        @NotNull(message = "Job type cannot be null")
        @NotBlank(message = "Job type cannot be blank")
        String type,

        @NotNull(message = "Job level id cannot be null")
        Long jobLevel,

        @NotNull(message = "Job level cannot be null")
        @NotBlank(message = "Job level cannot be blank")
        String level
) {
}
