package com.training.ats.services;

import com.training.ats.repositories.AtsUserRepository;
import com.training.ats.dto.RecruiterRecord;
import com.training.ats.dto.ResponseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterService implements GenericServiceInterface<RecruiterRecord, String> {

    @Autowired
    private AtsUserRepository recruiterRepository;

    @Override
    public List<RecruiterRecord> getAll() {
        return null;
    }

    @Override
    public RecruiterRecord getById(String id) {
        return null;
    }

    @Override
    public ResponseRecord deleteById(String id) {
        return null;
    }

    @Override
    public ResponseRecord deleteAll() {
        return null;
    }

    @Override
    public ResponseRecord update(RecruiterRecord object, String id) {
        return null;
    }

    @Override
    public ResponseRecord save(RecruiterRecord object) {
        return null;
    }
}
