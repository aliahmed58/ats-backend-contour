package com.training.ats.services;

import com.training.ats.models.Job;
import com.training.ats.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service for applicants containing all the business logic that will be used in the controller
 */
@Service
public class ApplicantService {

  @Autowired
  private JobRepository jobRepository;

  public List<Job> getAllJobs() {
    return jobRepository.findAll();
  }

}
