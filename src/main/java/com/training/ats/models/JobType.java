package com.training.ats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * job type table including type of job and level of seniority
 */
@Entity
public class JobType {

  @Id
  @GeneratedValue
  private Long jobTypeId;

  @NotNull(message = "Job type cannot be null")
  @NotBlank(message = "Job type cannot be blank")
  private String type;

  @NotNull(message = "Job level cannot be null")
  @ManyToOne
  @JoinColumn(name = "level_id", nullable = false)
  private Level jobLevel;

  public JobType() {}

  public JobType(Long jobTypeId, String type, Level jobLevel) {
    this.jobTypeId = jobTypeId;
    this.type = type;
    this.jobLevel = jobLevel;
  }

  public Long getJobTypeId() {
    return jobTypeId;
  }

  public void setJobTypeId(Long jobTypeId) {
    this.jobTypeId = jobTypeId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Level getJobLevel() {
    return jobLevel;
  }

  public void setJobLevel(Level jobLevel) {
    this.jobLevel = jobLevel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    JobType jobType = (JobType) o;
    return Objects.equals(jobTypeId, jobType.jobTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobTypeId);
  }
}
