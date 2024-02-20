package com.training.ats.controllers;

import com.training.ats.dto.AllApplicationRecord;
import com.training.ats.dto.ApplicationRecord;
import com.training.ats.services.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController extends BaseController<ApplicationRecord, Long> {

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @Secured("ROLE_RECRUITER")
    @GetMapping("/all")
    public ResponseEntity<List<AllApplicationRecord>> recruiterApplications() {
        ApplicationService applicationService = (ApplicationService) service;
        return ResponseEntity.ok(applicationService.getApplicationsForRecruiter());
    }
}
