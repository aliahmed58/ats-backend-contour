package com.training.ats.controllers;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.dto.AtsUserRecord;
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
public class RecruiterController implements GenericControllerInterface<AtsUserRecord, String> {

    @Autowired
    private RecruiterService recruiterService;


    @Override
    public ResponseEntity<List<AtsUserRecord>> get() {
        return ResponseEntity.ok(recruiterService.get());
    }

    @Override
    public ResponseEntity<AtsUserRecord> get(@PathVariable String id) {
        return ResponseEntity.ok(recruiterService.get(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete(@PathVariable String id) {
        return ResponseEntity.ok(recruiterService.delete(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete() {
        return ResponseEntity.ok(recruiterService.delete());
    }

    @Override
    public ResponseEntity<ResponseRecord> post(@RequestBody @Valid AtsUserRecord object, @PathVariable String id) {
        return ResponseEntity.ok(recruiterService.post(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> put(@RequestBody @Valid AtsUserRecord object) {
        return ResponseEntity.ok(recruiterService.put(object));
    }
}
