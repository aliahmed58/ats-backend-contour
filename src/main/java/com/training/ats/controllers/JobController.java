package com.training.ats.controllers;

import com.training.ats.dto.JobRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.models.Job;
import com.training.ats.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
        return ResponseEntity.ok(jobService.getAll());
    }

    @Override
    public ResponseEntity<JobRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.deleteById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteAll() {
        return ResponseEntity.ok(jobService.deleteAll());
    }

    @Override
    public ResponseEntity<ResponseRecord> update(@RequestBody JobRecord object, @PathVariable Long id) {
        return ResponseEntity.ok(jobService.update(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> save(@RequestBody JobRecord object) {
        return ResponseEntity.ok(jobService.save(object));
    }
}
