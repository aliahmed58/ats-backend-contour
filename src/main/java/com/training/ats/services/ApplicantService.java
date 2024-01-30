package com.training.ats.services;

import com.training.ats.models.Application;
import com.training.ats.models.AtsUser;
import com.training.ats.models.Job;
import com.training.ats.models.RoleType;
import com.training.ats.repositories.ApplicationRepository;
import com.training.ats.repositories.JobRepository;
import com.training.ats.responsedto.ApplicantRecord;
import com.training.ats.responsedto.ApplicationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * service for applicants containing all the business logic that will be used in the controller
 */
@Service
public class ApplicantService {

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private ApplicationRepository applicationRepository;

  public ApplicantRecord getApplicantProfile() {
    // get user from authentication context
    AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    // check if user is applicant or not
    if (user.getRoleType() == RoleType.APPLICANT) {
      // return record type, do not expose the actual user object
      List<Application> applications = applicationRepository.findByApplicant(user);
      // map to record objects using stream
      List<ApplicationRecord> applicationRecords = applications
              .stream()
              .map(application ->
                new ApplicationRecord(
                        application.getApplicationId(), application.getDateOfApply(),
                        application.getDescription(), application.getApplicationStatus(),
                        application.getJob()
                ))
              .collect(Collectors.toList());
      return new ApplicantRecord(user.getFirstName(), user.getLastName(), user.getUsername(), applicationRecords);
    }
    else {
      throw new SecurityException("Unauthorized. Access denied");
    }

  }

  public List<Job> getAllJobs() {
    return jobRepository.findAll();
  }

}
