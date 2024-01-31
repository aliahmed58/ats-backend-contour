package com.training.ats.controllers;

import com.training.ats.dto.ApplicationRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.models.Job;
import com.training.ats.dto.ApplicantRecord;
import com.training.ats.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/applicant")
@Secured("ROLE_APPLICANT")
public class ApplicantController implements GenericControllerInterface<ApplicationRecord, String> {

  @Autowired
  private ApplicantService applicantService;

  @Override
  public ResponseEntity<List<ApplicationRecord>> getAll() {
    return null;
  }

  @Override
  public ResponseEntity<ApplicationRecord> getById(String id) {
    return null;
  }

  @Override
  public ResponseEntity<ResponseRecord> deleteById(String id) {
    return null;
  }

  @Override
  public ResponseEntity<ResponseRecord> deleteAll() {
    return null;
  }

  @Override
  public ResponseEntity<ResponseRecord> update(ApplicationRecord object, String id) {
    return null;
  }

  @Override
  public ResponseEntity<ResponseRecord> save(ApplicationRecord object) {
    return null;
  }
}
