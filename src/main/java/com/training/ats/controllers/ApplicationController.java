package com.training.ats.controllers;

import com.training.ats.dto.ApplicationRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController implements GenericControllerInterface<ApplicationRecord, Long> {

    @Autowired
    private ApplicationService applicationService;

    @Override
    public ResponseEntity<List<ApplicationRecord>> get() {
        return ResponseEntity.ok(applicationService.get());
    }

    @Override
    public ResponseEntity<ApplicationRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.get(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.delete(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete() {
        return ResponseEntity.ok(applicationService.delete());
    }

    @Override
    public ResponseEntity<ResponseRecord> post(@RequestBody @Valid ApplicationRecord object, @PathVariable  Long id) {
        return ResponseEntity.ok(applicationService.post(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> put(@RequestBody @Valid ApplicationRecord object) {
        return ResponseEntity.ok(applicationService.put(object));
    }
}
