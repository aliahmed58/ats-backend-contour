package com.training.ats.controllers;

import com.training.ats.dto.JobRecord;
import com.training.ats.dto.ResponseRecord;
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
    public ResponseEntity<ResponseRecord> deleteById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteAll() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseRecord> update(JobRecord object, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseRecord> save(JobRecord object) {
        return null;
    }
}
