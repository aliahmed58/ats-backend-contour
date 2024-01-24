package com.training.ats.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "recruiters")
public class Recruiter extends BaseUser {

  @NotNull
  @OneToMany(mappedBy = "recruiter")
  private List<Applicant> applicants = new ArrayList<>();

  public Recruiter() {}

  public Recruiter(String username, String firstName, String lastName, List<Applicant> applicants) {
    super(username, firstName, lastName);
    this.applicants = applicants;
  }

  public Recruiter(String username, String firstName, String lastName) {
    super(username, firstName, lastName);
  }

  public List<Applicant> getApplicants() {
    return applicants;
  }

  public void setApplicants(List<Applicant> applicants) {
    this.applicants = applicants;
  }
}
