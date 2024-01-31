package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;
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
    public ResponseRecord deleteById(Long id) {
        return  null;
    }

    @Override
    public ResponseRecord deleteAll() {
        return  null;
    }

    @Override
    public ResponseRecord update(ApplicationRecord object, Long id) {
        return  null;
    }

    @Override
    public ResponseRecord save(ApplicationRecord object) {
        return  null;
    }
}
