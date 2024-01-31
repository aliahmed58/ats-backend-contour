package com.training.ats.controllers;

import com.training.ats.dto.LevelRecord;
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
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(LevelRecord object, Long id) {

    }

    @Override
    public void save(LevelRecord object) {

    }
}
