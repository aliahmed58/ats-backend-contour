package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.repositories.LevelRepository;
import com.training.ats.dto.LevelRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService implements GenericServiceInterface<LevelRecord, Long> {

    @Autowired
    private LevelRepository levelRepository;
    @Override
    public List<LevelRecord> getAll() {
        return null;
    }

    @Override
    public LevelRecord getById(Long id) {
        return null;
    }

    @Override
    public ResponseRecord deleteById(Long id) {
        return null;
    }

    @Override
    public ResponseRecord deleteAll() {
        return null;
    }

    @Override
    public ResponseRecord update(LevelRecord object, Long id) {
        return null;
    }

    @Override
    public ResponseRecord save(LevelRecord object) {
        return null;
    }
}
