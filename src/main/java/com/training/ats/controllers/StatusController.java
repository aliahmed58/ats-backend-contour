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
    public ResponseEntity<List<StatusRecord>> get() {
        return ResponseEntity.ok(statusService.get());
    }

    @Override
    public ResponseEntity<StatusRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.get(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete(@PathVariable Long id) {
        return ResponseEntity.ok(statusService.delete(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete() {
        return ResponseEntity.ok(statusService.delete());
    }

    @Override
    public ResponseEntity<ResponseRecord> post(@RequestBody @Valid StatusRecord object, @PathVariable Long id) {
        return ResponseEntity.ok(statusService.post(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> put(@RequestBody @Valid StatusRecord object) {
        return ResponseEntity.ok(statusService.put(object));
    }
}
