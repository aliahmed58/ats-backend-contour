package com.training.ats.controllers;

import com.training.ats.dto.LevelRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return null;
    }

    @Override
    public ResponseEntity<LevelRecord> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseRecord> deleteAll() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseRecord> update(LevelRecord object, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseRecord> save(LevelRecord object) {
        return null;
    }
}
