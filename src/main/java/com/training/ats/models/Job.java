package com.training.ats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * job entity for jobs
 */
@Entity
@Table(name = "jobs")
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
  @OneToOne
  @JoinColumn(name = "job_type_id", nullable = false)
  private JobType jobType;

  public Job(Long jobId, String jobName, String jobDescription, JobType jobType) {
    this.jobId = jobId;
    this.jobName = jobName;
    this.jobDescription = jobDescription;
    this.jobType = jobType;
  }

  public Job() {}

  public Long getJobId() {
    return jobId;
  }

  public void setJobId(Long jobId) {
    this.jobId = jobId;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public String getJobDescription() {
    return jobDescription;
  }

  public void setJobDescription(String jobDescription) {
    this.jobDescription = jobDescription;
  }

  public JobType getJobType() {
    return jobType;
  }

  public void setJobType(JobType jobType) {
    this.jobType = jobType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Job job = (Job) o;
    return Objects.equals(jobId, job.jobId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobId);
  }
}
