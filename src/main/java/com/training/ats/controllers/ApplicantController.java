package com.training.ats.controllers;

import com.training.ats.dto.AtsUserRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applicant")
public class ApplicantController implements GenericControllerInterface<AtsUserRecord, String> {
  @Autowired
  private ApplicantService applicantService;


  @Secured("ROLE_RECRUITER")
  @Override
  public ResponseEntity<List<AtsUserRecord>> getAll() {
    return ResponseEntity.ok(applicantService.getAll());
  }


  @Secured({"ROLE_APPLICANT", "ROLE_RECRUITER"})
  @Override
  public ResponseEntity<AtsUserRecord> getById(@PathVariable String id) {
    return ResponseEntity.ok(applicantService.getById(id));
  }

  @Secured("ROLE_APPLICANT")
  @Override
  public ResponseEntity<ResponseRecord> deleteById(@PathVariable String id) {
    return ResponseEntity.ok(applicantService.deleteById(id));
  }
  @Secured("ROLE_RECRUITER")
  @Override
  public ResponseEntity<ResponseRecord> deleteAll() {
    return ResponseEntity.ok(applicantService.deleteAll());
  }

  @Secured("ROLE_APPLICANT")
  @Override
  public ResponseEntity<ResponseRecord> update(@RequestBody AtsUserRecord object, @PathVariable String id) {
    return ResponseEntity.ok(applicantService.update(object, id));
  }

  @Secured("ROLE_APPLICANT")
  @Override
  public ResponseEntity<ResponseRecord> save(@RequestBody AtsUserRecord object) {
    return ResponseEntity.ok(applicantService.save(object));
  }
}
