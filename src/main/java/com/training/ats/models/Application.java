package com.training.ats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * applications table to hold application data of applicants
 */
@Entity
@Table(name = "applications")
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
  @OneToOne
  @JoinColumn(name = "status_id", nullable = false)
  private Status applicationStatus;

  @NotNull(message = "applicant cannot be null")
  @ManyToOne
  @JoinColumn(name = "applicant_id", nullable = false)
  private Applicant applicant;

  @NotNull(message = "job cannot be null")
  @ManyToOne
  @JoinColumn(name = "job_id", nullable = false)
  private Job job;

  public Application() {
  }

  public Long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(Long applicationId) {
    this.applicationId = applicationId;
  }

  public LocalDate getDateOfApply() {
    return dateOfApply;
  }

  public void setDateOfApply(LocalDate dateOfApply) {
    this.dateOfApply = dateOfApply;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Status getApplicationStatus() {
    return applicationStatus;
  }

  public void setApplicationStatus(Status applicationStatus) {
    this.applicationStatus = applicationStatus;
  }

  public Applicant getApplicant() {
    return applicant;
  }

  public void setApplicant(Applicant applicant) {
    this.applicant = applicant;
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Application that = (Application) o;
    return Objects.equals(applicationId, that.applicationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId);
  }
}
