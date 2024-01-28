package com.training.ats.controllers;

import com.training.ats.models.Job;
import com.training.ats.repositories.ApplicationRepository;
import com.training.ats.repositories.JobRepository;
import com.training.ats.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * rest controller for handling applicant end points such as accessing applications,
 * creating applications and so on
 */

@RestController
public class ApplicantController {

  @Autowired
  private ApplicantService applicantService;

  @GetMapping("/api/home")
  public ResponseEntity<String> applicantHome() {

    return ResponseEntity.ok("test");
  }

  @GetMapping("/api/view_jobs")
  public ResponseEntity<List<Job>> viewJobs() {
    return ResponseEntity.ok(applicantService.getAllJobs());
  }
}
