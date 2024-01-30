package com.training.ats.models;

import ch.qos.logback.core.status.StatusUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * application status: pending, rejected, etc.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Status {
  @Id
  @GeneratedValue
  private long statusId;

  @NotNull(message = "Status cannot be null")
  @NotBlank(message = "Status cannot be blank")
  private String statusType;
}
