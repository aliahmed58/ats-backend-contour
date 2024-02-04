package com.training.ats.controllers;

import com.training.ats.dto.AddJobRecord;
import com.training.ats.dto.RecruiterRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * rest controller for recruiter routes /admin
 * only available for authenticated users with role type of ROLE_RECRUITER
 */
@RestController
@RequestMapping("/recruiter")
@Secured("ROLE_RECRUITER")
public class RecruiterController implements GenericControllerInterface<RecruiterRecord, String> {

    @Autowired
    private RecruiterService recruiterService;


    @Override
    public ResponseEntity<List<RecruiterRecord>> getAll() {
        return ResponseEntity.ok(recruiterService.getAll());
    }

    @Override
    public ResponseEntity<RecruiterRecord> getById(@PathVariable String id) {
        return ResponseEntity.ok(recruiterService.getById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteById(@PathVariable String id) {
        return ResponseEntity.ok(recruiterService.deleteById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteAll() {
        return ResponseEntity.ok(recruiterService.deleteAll());
    }

    @Override
    public ResponseEntity<ResponseRecord> update(@RequestBody @Valid RecruiterRecord object, @PathVariable String id) {
        return ResponseEntity.ok(recruiterService.update(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> save(@RequestBody @Valid RecruiterRecord object) {
        return ResponseEntity.ok(recruiterService.save(object));
    }
}
