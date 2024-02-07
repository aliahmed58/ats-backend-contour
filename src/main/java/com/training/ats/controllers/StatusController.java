package com.training.ats.controllers;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.dto.StatusRecord;
import com.training.ats.services.StatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status")
@Secured("ROLE_RECRUITER")
public class StatusController extends BaseController<StatusRecord, Long> {
   public StatusController(StatusService service) {
        this.service = service;
   }
}
