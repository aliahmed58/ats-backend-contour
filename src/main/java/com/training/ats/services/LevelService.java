package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.models.Level;
import com.training.ats.repositories.LevelRepository;
import com.training.ats.dto.LevelRecord;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LevelService implements GenericServiceInterface<LevelRecord, Long> {

    @Autowired
    private LevelRepository levelRepository;

    // get output, map to records and return as list
    @Override
    public List<LevelRecord> getAll() {
        List<Level> levels = levelRepository.findAll();
        return levels
                .stream()
                .map(level -> new LevelRecord(level.getLevel()))
                .collect(Collectors.toList());
    }

    @Override
    public LevelRecord getById(Long id) {
        Optional<Level> level = levelRepository.findById(id);
        if (level.isEmpty())
            throw new EntityNotFoundException("level not found");
        return new LevelRecord(level.get().getLevel());
    }


    @Override
    public ResponseRecord deleteById(Long id) {
        levelRepository.deleteById(id);
        return new ResponseRecord(HttpStatus.OK.value(), "Level deleted");
    }

    @Override
    public ResponseRecord deleteAll() {
        levelRepository.deleteAll();
        return new ResponseRecord(HttpStatus.OK.value(), "All levels deleted");
    }

    @Transactional
    @Override
    public ResponseRecord update(LevelRecord object, Long id) {
        levelRepository.updateLevelById(object.level(), id);
        return new ResponseRecord(HttpStatus.OK.value(), "Level updated");
    }

    @Override
    public ResponseRecord save(LevelRecord object) {
        levelRepository.save(
                Level.builder().level(object.level()).build()
        );
        return new ResponseRecord(HttpStatus.OK.value(), "Level saved");
    }
}
