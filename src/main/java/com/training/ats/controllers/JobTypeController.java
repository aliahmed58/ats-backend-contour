package com.training.ats.controllers;

import com.training.ats.dto.JobTypeRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.JobTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
        return ResponseEntity.ok(jobTypeService.getAll());
    }

    @Override
    public ResponseEntity<JobTypeRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(jobTypeService.getById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(jobTypeService.deleteById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteAll() {
        return ResponseEntity.ok(jobTypeService.deleteAll());
    }

    @Override
    public ResponseEntity<ResponseRecord> update(@RequestBody @Valid JobTypeRecord object, @PathVariable  Long id) {
        return ResponseEntity.ok(jobTypeService.update(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> save(@RequestBody @Valid JobTypeRecord object) {
        return ResponseEntity.ok(jobTypeService.save(object));
    }
}
