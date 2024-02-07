package com.training.ats.controllers;

import com.training.ats.dto.JobTypeRecord;
import com.training.ats.services.JobTypeService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/job_type")
@Secured("ROLE_RECRUITER")
public class JobTypeController extends BaseController<JobTypeRecord, Long> {
    public JobTypeController(JobTypeService service) {
        this.service = service;
    }
}
