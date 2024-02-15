package com.training.ats.dto;

import java.util.List;

public record ApplicantProfileRecord (
        String username,
        String firstName,
        String lastName,
        List<StatusCount> statusCounts
) {}
