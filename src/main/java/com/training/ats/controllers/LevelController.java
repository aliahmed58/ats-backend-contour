package com.training.ats.controllers;

import com.training.ats.dto.LevelRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.LevelService;
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
@RequestMapping("/levels")
@Secured("ROLE_RECRUITER")
public class LevelController extends BaseController<LevelRecord, Long> {

    public LevelController(LevelService service) {
        this.service = service;
    }
}
