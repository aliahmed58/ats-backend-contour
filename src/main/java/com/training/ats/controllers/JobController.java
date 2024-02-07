package com.training.ats.controllers;

import com.training.ats.dto.JobRecord;
import com.training.ats.dto.ResponseRecord;
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
    public ResponseEntity<List<JobRecord>> get() {
        return ResponseEntity.ok(jobService.get());
    }

    @Override
    public ResponseEntity<JobRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.get(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.delete(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete() {
        return ResponseEntity.ok(jobService.delete());
    }

    @Override
    public ResponseEntity<ResponseRecord> post(@RequestBody JobRecord object, @PathVariable Long id) {
        return ResponseEntity.ok(jobService.post(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> put(@RequestBody JobRecord object) {
        return ResponseEntity.ok(jobService.put(object));
    }
}
