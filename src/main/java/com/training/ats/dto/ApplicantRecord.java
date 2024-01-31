package com.training.ats.dto;

import java.util.List;

/**
 * record to send applicant data as a response entity
 */
public record ApplicantRecord(
        String firstname,
        String lastname,
        String username,
        List<ApplicationRecord> applications
) {}
