package com.training.ats.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "applicants")
public class Applicant extends BaseUser {

  @NotNull(message = "An applicant must have a recruiter")
  @ManyToOne
  @JoinColumn(name = "recruiter_id", nullable = false)
  private Recruiter recruiter;

  public Applicant() {}
  public Applicant(String username, String firstName, String lastName, Recruiter recruiter) {
    super(username, firstName, lastName);
    this.recruiter = recruiter;
  }

  public Recruiter getRecruiter() {
    return recruiter;
  }

  public void setRecruiter(Recruiter recruiter) {
    this.recruiter = recruiter;
  }
}
