package com.training.ats.responsedto;

import java.util.List;

public record ApplicantRecord(
        String firstname,
        String lastname,
        String username,
        List<ApplicationRecord> applications
) {}
