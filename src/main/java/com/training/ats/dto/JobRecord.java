package com.training.ats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobRecord(

        @NotNull(message = "job name cannot be null")
        @NotBlank(message = "job name cannot be blank")
        String jobName,

        @NotNull(message = "job desc cannot be null")
        @NotBlank(message = "job desc cannot be blank")
        String jobDesc,

        @NotNull(message = "job type id cannot be null")
        Long jobTypeId
) {
}
