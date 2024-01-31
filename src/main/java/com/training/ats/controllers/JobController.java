package com.training.ats.controllers;

import com.training.ats.dto.JobRecord;
import com.training.ats.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController implements GenericControllerInterface<JobRecord, Long> {

    @Autowired
    private JobService jobService;

    @Override
    public ResponseEntity<List<JobRecord>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<JobRecord> getById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(JobRecord object, Long id) {

    }

    @Override
    public void save(JobRecord object) {

    }
}
