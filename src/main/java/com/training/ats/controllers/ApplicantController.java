package com.training.ats.controllers;

import com.training.ats.dto.ApplicantProfileRecord;
import com.training.ats.dto.AtsUserRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.ApplicantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicant")
public class ApplicantController extends BaseController<AtsUserRecord, String> {

  public ApplicantController(ApplicantService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<List<AtsUserRecord>> get() {
    return super.get();
  }


  @Secured({"ROLE_APPLICANT", "ROLE_RECRUITER"})
  @Override
  public ResponseEntity<AtsUserRecord> get(@PathVariable String id) {
    return super.get(id);
  }

  @Secured("ROLE_APPLICANT")
  @Override
  public ResponseEntity<ResponseRecord> delete(@PathVariable String id) {
    return super.delete(id);
  }

  @Secured("ROLE_RECRUITER")
  @Override
  public ResponseEntity<ResponseRecord> delete() {
    return super.delete();
  }

  @Secured("ROLE_APPLICANT")
  @Override
  public ResponseEntity<ResponseRecord> post(@RequestBody AtsUserRecord object, @PathVariable String id) {
    return super.post(object, id);
  }

  @Secured("ROLE_APPLICANT")
  @Override
  public ResponseEntity<ResponseRecord> put(@RequestBody AtsUserRecord object) {
    return super.put(object);
  }

  @GetMapping("/profile/{id}")
  public ResponseEntity<ApplicantProfileRecord> profile(@PathVariable String id) {
      ApplicantService applicantService = (ApplicantService) service;
      return ResponseEntity.ok(applicantService.applicantProfileRecord(id));
  }
}
