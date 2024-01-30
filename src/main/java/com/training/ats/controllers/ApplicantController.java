package com.training.ats.controllers;

import com.training.ats.models.Job;
import com.training.ats.responsedto.ApplicantRecord;
import com.training.ats.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * /applicant
 * rest controller for handling applicant end points such as accessing applications,
 * creating applications and so on
 */

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

  @Autowired
  private ApplicantService applicantService;

  /**
   * api endpoint to get applicant profile
   * @return ApplicantRecord object containing applicant data
   */
  @GetMapping("/profile")
  public ResponseEntity<ApplicantRecord> applicantProfile() {
    return ResponseEntity.ok(applicantService.getApplicantProfile());
  }

  @GetMapping("/view_jobs")
  public ResponseEntity<List<Job>> viewJobs() {
    return ResponseEntity.ok(applicantService.getAllJobs());
  }
}
