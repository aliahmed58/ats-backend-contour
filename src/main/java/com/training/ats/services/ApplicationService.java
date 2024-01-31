package com.training.ats.services;

import com.training.ats.repositories.ApplicationRepository;
import com.training.ats.dto.ApplicationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService implements GenericServiceInterface<ApplicationRecord, Long> {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<ApplicationRecord> getAll() {
        return null;
    }

    @Override
    public ApplicationRecord getById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(ApplicationRecord object, Long id) {

    }

    @Override
    public void save(ApplicationRecord object) {

    }
}
