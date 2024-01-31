package com.training.ats.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddJobRecord(
        @NotNull(message = "Job title cannot be null")
        @NotBlank(message = "Job title cannot be blank")
        String jobTitle,

        @NotNull(message = "Job description cannot be null")
        @NotBlank(message = "Job description cannot be blank")
        String jobDesc,

        @NotNull(message = "Job type cannot be null")
        Long jobTypeId,

        @NotNull(message = "Job level cannot be null")
        Long jobLevelId

) {
}
