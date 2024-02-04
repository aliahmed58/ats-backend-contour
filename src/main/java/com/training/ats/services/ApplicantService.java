package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.repositories.AtsUserRepository;
import com.training.ats.dto.ApplicantRecord;
import com.training.ats.dto.ApplicationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service for applicants containing all the business logic that will be used in the controller
 */
@Service
public class ApplicantService implements GenericServiceInterface<ApplicantRecord, String> {


  @Autowired
  private AtsUserRepository applicantRepository;
  @Override
  public List<ApplicantRecord> getAll() {
    return null;
  }

  @Override
  public ApplicantRecord getById(String id) {
    return null;
  }

  @Override
  public ResponseRecord deleteById(String id) {
    return null;
  }

  @Override
  public ResponseRecord deleteAll() {
    return null;
  }

  @Override
  public ResponseRecord update(ApplicantRecord object, String id) {
    return null;
  }

  @Override
  public ResponseRecord save(ApplicantRecord object) {
    return null;
  }
}
