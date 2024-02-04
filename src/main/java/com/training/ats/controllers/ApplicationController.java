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
    public ResponseEntity<List<ApplicationRecord>> getAll() {
        return ResponseEntity.ok(applicationService.getAll());
    }

    @Override
    public ResponseEntity<ApplicationRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.deleteById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteAll() {
        return ResponseEntity.ok(applicationService.deleteAll());
    }

    @Override
    public ResponseEntity<ResponseRecord> update(@RequestBody @Valid ApplicationRecord object, @PathVariable  Long id) {
        return ResponseEntity.ok(applicationService.update(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> save(@RequestBody @Valid ApplicationRecord object) {
        return ResponseEntity.ok(applicationService.save(object));
    }
}
