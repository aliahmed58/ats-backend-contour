package com.training.ats.controllers;

import com.training.ats.dto.LevelRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.LevelService;
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
@RequestMapping("/levels")
@Secured("ROLE_RECRUITER")
public class LevelController implements GenericControllerInterface<LevelRecord, Long> {

    @Autowired
    private LevelService levelService;

    @Override
    public ResponseEntity<List<LevelRecord>> get() {
        return ResponseEntity.ok(levelService.get());
    }

    @Override
    public ResponseEntity<LevelRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(levelService.get(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete(@PathVariable Long id) {
        return ResponseEntity.ok(levelService.delete(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> delete() {
        return ResponseEntity.ok(levelService.delete());
    }

    @Override
    public ResponseEntity<ResponseRecord> post(@RequestBody @Valid LevelRecord object, @PathVariable Long id) {
        return ResponseEntity.ok(levelService.post(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> put(@RequestBody @Valid LevelRecord object) {
        return ResponseEntity.ok(levelService.put(object));
    }
}
