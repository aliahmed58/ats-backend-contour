package com.training.ats.controllers;

import com.training.ats.responsedto.RecruiterRecord;
import com.training.ats.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rest controller for recruiter routes /admin
 */
@RestController
@RequestMapping("/admin")
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
}
