package com.training.ats.services;


import com.training.ats.dto.ResponseRecord;
import com.training.ats.repositories.JobRepository;
import com.training.ats.dto.JobRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements GenericServiceInterface<JobRecord, Long> {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<JobRecord> getAll() {
        return null;
    }

    @Override
    public JobRecord getById(Long id) {
        return null;
    }

    @Override
    public ResponseRecord deleteById(Long id) {
        return  null;
    }

    @Override
    public ResponseRecord deleteAll() {
        return null;
    }

    @Override
    public ResponseRecord update(JobRecord object, Long id) {
        return null;
    }

    @Override
    public ResponseRecord save(JobRecord object) {
        return null;
    }
}
