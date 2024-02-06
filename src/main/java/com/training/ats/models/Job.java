package com.training.ats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * job entity for jobs
 */
@Entity
@Table(name = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {

  @Id
  @GeneratedValue
  private Long jobId;

  @NotNull(message = "Job name cannot be null")
  @NotBlank(message = "Job name cannot be blank")
  private String jobName;

  @NotNull(message = "Job description cannot be null")
  @NotBlank(message = "Job description cannot be blank")
  private String jobDescription;

  @NotNull(message = "Job - Job type cannot be null")
  @ManyToOne
  @JoinColumn(name = "job_type_id", nullable = false)
  private JobType jobType;

}
