package com.training.ats.services;

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
