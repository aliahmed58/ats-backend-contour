package com.training.ats.controllers;

import com.training.ats.dto.AtsUserRecord;
import com.training.ats.services.RecruiterService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * rest controller for recruiter routes /admin
 * only available for authenticated users with role type of ROLE_RECRUITER
 */
@RestController
@RequestMapping("/recruiter")
@Secured("ROLE_RECRUITER")
public class RecruiterController extends BaseController<AtsUserRecord, String> {

    public RecruiterController(RecruiterService service) {
        this.service = service;
    }
}
