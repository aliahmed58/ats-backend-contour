package com.training.ats.controllers;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.dto.StatusRecord;
import com.training.ats.services.StatusService;
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
@RequestMapping("/status")
@Secured("ROLE_RECRUITER")
public class StatusController implements GenericControllerInterface<StatusRecord, Long> {
    @Autowired
    private StatusService statusService;
    @Override
    public ResponseEntity<List<StatusRecord>> getAll() {
        return ResponseEntity.ok(statusService.getAll());
    }

    @Override
    public ResponseEntity<StatusRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.getById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.deleteById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteAll() {
        return ResponseEntity.ok(statusService.deleteAll());
    }

    @Override
    public ResponseEntity<ResponseRecord> update(@RequestBody @Valid StatusRecord object, @PathVariable Long id) {
        return ResponseEntity.ok(statusService.update(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> save(@RequestBody @Valid StatusRecord object) {
        return ResponseEntity.ok(statusService.save(object));
    }
}
