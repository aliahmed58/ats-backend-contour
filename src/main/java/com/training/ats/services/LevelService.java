package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.exceptions.ErrorMessageBuilder;
import com.training.ats.exceptions.ErrorType;
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

    private final static String LOGGER = "Level logger";
    @Autowired
    private LevelRepository levelRepository;

    // get output, map to records and return as list
    @Override
    public List<LevelRecord> get() {
        List<Level> levels = levelRepository.findAll();
        return levels
                .stream()
                .map(level -> new LevelRecord(level.getLevel()))
                .collect(Collectors.toList());
    }

    @Override
    public LevelRecord get(Long id) {
        Optional<Level> level = levelRepository.findById(id);
        if (level.isEmpty())
            throw new EntityNotFoundException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_NOT_FOUND));
        return new LevelRecord(level.get().getLevel());
    }


    @Override
    public ResponseRecord delete(Long id) {
        levelRepository.deleteById(id);
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_DELETED));
    }

    @Override
    public ResponseRecord delete() {
        levelRepository.deleteAll();
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_DELETED_ALL));
    }

    @Transactional
    @Override
    public ResponseRecord post(LevelRecord object, Long id) {
        levelRepository.updateLevelById(object.level(), id);
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_UPDATED));
    }

    @Override
    public ResponseRecord put(LevelRecord object) {
        levelRepository.save(
                Level.builder().level(object.level()).build()
        );
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_SAVED));
    }
}
