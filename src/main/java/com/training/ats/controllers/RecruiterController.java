package com.training.ats.controllers;

import com.training.ats.dto.AddJobRecord;
import com.training.ats.dto.RecruiterRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * rest controller for recruiter routes /admin
 * only available for authenticated users with role type of ROLE_RECRUITER
 */
@RestController
@RequestMapping("/admin")
@Secured("ROLE_RECRUITER")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    /**
     * api end point for recruiter profile
     * @return response with recruiter profile if authorized
     */
    @GetMapping("/profile")
    public ResponseEntity<RecruiterRecord> recruiterProfile() {
        return ResponseEntity.ok(recruiterService.getRecruiterProfile());
    }

    @PostMapping("/addJob")
    public ResponseEntity<ResponseRecord> addNewJob(@RequestBody @Valid AddJobRecord newJobRecord) {
        return ResponseEntity.ok(recruiterService.addNewJob(newJobRecord));
    }
}
