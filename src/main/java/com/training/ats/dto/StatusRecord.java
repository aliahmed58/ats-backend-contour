package com.training.ats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StatusRecord(
        @NotNull(message = "Status cannot be null")
        @NotBlank(message = "Status cannot be blank")
        String statusType
) {}
