package com.training.ats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

/**
 * applications table to hold application data of applicants
 */
@Entity
@Table(name = "applications")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Application {

  @Id
  @GeneratedValue
  private Long applicationId;

  @NotNull(message = "Application date cannot be null")
  private LocalDate dateOfApply;

  @NotNull(message = "Description cannot be null")
  @NotBlank(message = "Description cannot be blank")
  private String description;

  @NotNull(message = "Status cannot be null")
  @ManyToOne
  @JoinColumn(name = "status_id", nullable = false)
  private Status applicationStatus;

  @NotNull(message = "applicant cannot be null")
  @ManyToOne
  @JoinColumn(name = "applicant_id", nullable = false)
  private AtsUser applicant;

  @NotNull(message = "job cannot be null")
  @ManyToOne
  @JoinColumn(name = "job_id", nullable = false)
  private Job job;

}
