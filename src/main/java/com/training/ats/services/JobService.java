package com.training.ats.services;


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
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(JobRecord object, Long id) {

    }

    @Override
    public void save(JobRecord object) {

    }
}
