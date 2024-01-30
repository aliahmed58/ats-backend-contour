package com.training.ats.responsedto;

import com.training.ats.models.Job;
import com.training.ats.models.Status;

import java.time.LocalDate;

public record ApplicationRecord(
        Long applicationId,
        LocalDate dateOfApply,
        String description,
        Status applicationStatus,
        Job job
) {}
