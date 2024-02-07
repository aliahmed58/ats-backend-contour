package com.training.ats.controllers;

import com.training.ats.dto.JobTypeRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.JobTypeService;
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
@RequestMapping("/job_type")
@Secured("ROLE_RECRUITER")
public class JobTypeController implements GenericControllerInterface<JobTypeRecord, Long> {

    @Autowired
    private JobTypeService jobTypeService;

    @Override
    public ResponseEntity<List<JobTypeRecord>> get() {
        return ResponseEntity.ok(jobTypeService.get());
    }

    @Override
    public ResponseEntity<JobTypeRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(jobTypeService.get(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete(@PathVariable Long id) {
        return ResponseEntity.ok(jobTypeService.delete(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete() {
        return ResponseEntity.ok(jobTypeService.delete());
    }

    @Override
    public ResponseEntity<ResponseRecord> post(@RequestBody @Valid JobTypeRecord object, @PathVariable  Long id) {
        return ResponseEntity.ok(jobTypeService.post(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> put(@RequestBody @Valid JobTypeRecord object) {
        return ResponseEntity.ok(jobTypeService.put(object));
    }
}
