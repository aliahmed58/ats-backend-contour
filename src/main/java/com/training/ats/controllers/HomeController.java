package com.training.ats.controllers;

import com.training.ats.models.Applicant;
import com.training.ats.models.Recruiter;
import com.training.ats.repositories.ApplicantRepository;
import com.training.ats.repositories.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

  @Autowired
  private ApplicantRepository applicantRepository;
  @Autowired
  private RecruiterRepository recruiterRepository;
  @GetMapping("/")
  public String home() {
    Recruiter recruiter = new Recruiter("ahmed", "test", "tes");
    recruiterRepository.save(recruiter);
    Applicant applicant = new Applicant("aliahmed", "test", "test", recruiter);
    applicantRepository.save(applicant);
    recruiter.getApplicants().add(applicant);
    return "Saved";
  }


  @GetMapping("/test")
  public String test() {
   return String.valueOf(recruiterRepository.findById("ahmed").get().getApplicants());

  }


}
