package com.training.ats.controllers;

import com.training.ats.dto.LevelRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.LevelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/levels")
public class LevelController implements GenericControllerInterface<LevelRecord, Long> {

    @Autowired
    private LevelService levelService;

    @Override
    public ResponseEntity<List<LevelRecord>> getAll() {
        return ResponseEntity.ok(levelService.getAll());
    }

    @Override
    public ResponseEntity<LevelRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(levelService.getById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(levelService.deleteById(id));
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteAll() {
        return ResponseEntity.ok(levelService.deleteAll());
    }

    @Override
    public ResponseEntity<ResponseRecord> update(@RequestBody @Valid LevelRecord object, @PathVariable Long id) {
        return ResponseEntity.ok(levelService.update(object, id));
    }

    @Override
    public ResponseEntity<ResponseRecord> save(@RequestBody @Valid LevelRecord object) {
        return ResponseEntity.ok(levelService.save(object));
    }
}
