package com.training.ats.dto;

import com.training.ats.models.Job;
import com.training.ats.models.Status;

import java.time.LocalDate;

/**
 * record object to send application data for users
 */
public record ApplicationRecord(
        Long applicationId,
        LocalDate dateOfApply,
        String description,
        Status applicationStatus,
        Job job
) {}
