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
  public ResponseEntity<List<AtsUserRecord>> get() {
    return ResponseEntity.ok(applicantService.get());
  }


  @Secured({"ROLE_APPLICANT", "ROLE_RECRUITER"})
  @Override
  public ResponseEntity<AtsUserRecord> get(@PathVariable String id) {
    return ResponseEntity.ok(applicantService.get(id));
  }

  @Secured("ROLE_APPLICANT")
  @Override
  public ResponseEntity<ResponseRecord> delete(@PathVariable String id) {
    return ResponseEntity.ok(applicantService.delete(id));
  }
  @Secured("ROLE_RECRUITER")
  @Override
  public ResponseEntity<ResponseRecord> delete() {
    return ResponseEntity.ok(applicantService.delete());
  }

  @Secured("ROLE_APPLICANT")
  @Override
  public ResponseEntity<ResponseRecord> post(@RequestBody AtsUserRecord object, @PathVariable String id) {
    return ResponseEntity.ok(applicantService.post(object, id));
  }

  @Secured("ROLE_APPLICANT")
  @Override
  public ResponseEntity<ResponseRecord> put(@RequestBody AtsUserRecord object) {
    return ResponseEntity.ok(applicantService.put(object));
  }
}
