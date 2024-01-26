package com.training.ats.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rest controller for handling applicant end points such as accesing applications,
 * creating applications and so on
 */

@RestController
public class ApplicantController {

  @GetMapping("/api/home")
  public ResponseEntity<String> applicantHome() {

    return ResponseEntity.ok("test");
  }
}
