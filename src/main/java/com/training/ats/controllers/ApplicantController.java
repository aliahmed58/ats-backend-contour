package com.training.ats.controllers;

import com.training.ats.dto.ApplicantRecord;
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
@Secured("ROLE_APPLICANT")
public class ApplicantController implements GenericControllerInterface<ApplicantRecord, String> {
  @Autowired
  private ApplicantService applicantService;
  @Override
  public ResponseEntity<List<ApplicantRecord>> getAll() {
    return ResponseEntity.ok(applicantService.getAll());
  }

  @Override
  public ResponseEntity<ApplicantRecord> getById(@PathVariable String id) {
    return ResponseEntity.ok(applicantService.getById(id));
  }

  @Override
  public ResponseEntity<ResponseRecord> deleteById(@PathVariable String id) {
    return ResponseEntity.ok(applicantService.deleteById(id));
  }

  @Override
  public ResponseEntity<ResponseRecord> deleteAll() {
    return ResponseEntity.ok(applicantService.deleteAll());
  }

  @Override
  public ResponseEntity<ResponseRecord> update(@RequestBody ApplicantRecord object, @PathVariable String id) {
    return ResponseEntity.ok(applicantService.update(object, id));
  }

  @Override
  public ResponseEntity<ResponseRecord> save(@RequestBody ApplicantRecord object) {
    return ResponseEntity.ok(applicantService.save(object));
  }
}
