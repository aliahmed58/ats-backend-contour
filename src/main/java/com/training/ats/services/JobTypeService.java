package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.repositories.JobTypeRepository;
import com.training.ats.dto.JobTypeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTypeService implements GenericServiceInterface<JobTypeRecord, Long> {

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Override
    public List<JobTypeRecord> getAll() {
        return null;
    }

    @Override
    public JobTypeRecord getById(Long id) {
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
    public ResponseRecord update(JobTypeRecord object, Long id) {
        return null;
    }

    @Override
    public ResponseRecord save(JobTypeRecord object) {
        return null;
    }
}