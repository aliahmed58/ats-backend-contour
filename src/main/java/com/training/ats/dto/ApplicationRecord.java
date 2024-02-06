package com.training.ats.dto;

import com.training.ats.models.Job;
import com.training.ats.models.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * record object to send application data for users
 */
public record ApplicationRecord(
        @NotNull(message = "Date of apply cannot be null")
        LocalDate dateOfApply,
        @NotNull(message = "description cannot be null")
        @NotBlank(message = "description cannot be blank")
        String description,
        @NotNull(message = "status id cannot be null")
        Long statusId,
        @NotNull(message = "job id cannot be null")
        Long jobId,
        @NotNull(message = "applicant id cannot be null")
        @NotBlank(message = "applicant id cannot be blank")
        String applicantId
) {
}
