package com.training.ats.controllers;

import com.training.ats.dto.ApplicationRecord;
import com.training.ats.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController implements GenericControllerInterface<ApplicationRecord, Long> {

    @Autowired
    private ApplicationService applicationService;

    @Override
    public ResponseEntity<List<ApplicationRecord>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<ApplicationRecord> getById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(ApplicationRecord object, Long id) {

    }

    @Override
    public void save(ApplicationRecord object) {

    }
}
