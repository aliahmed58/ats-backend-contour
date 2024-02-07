package com.training.ats.controllers;

import com.training.ats.dto.ApplicationRecord;
import com.training.ats.services.ApplicationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController extends BaseController<ApplicationRecord, Long> {

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }
}
