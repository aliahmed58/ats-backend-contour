package com.training.ats.controllers;

import com.training.ats.dto.JobTypeRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job_type")
public class JobTypeController implements GenericControllerInterface<JobTypeRecord, Long> {

    @Autowired
    private JobTypeService jobTypeService;

    @Override
    public ResponseEntity<List<JobTypeRecord>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<JobTypeRecord> getById(Long id) {
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
    public ResponseEntity<ResponseRecord> update(JobTypeRecord object, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseRecord> save(JobTypeRecord object) {
        return null;
    }
}
