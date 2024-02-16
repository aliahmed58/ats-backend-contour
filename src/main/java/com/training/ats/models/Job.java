package com.training.ats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
  @Column(length =500)
  private String jobDescription;

  @NotNull(message = "Job - Job type cannot be null")
  @ManyToOne
  @JoinColumn(name = "job_type_id", nullable = false)
  private JobType jobType;

  @OneToMany(mappedBy = "applicationId", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<Application> applications;

}
