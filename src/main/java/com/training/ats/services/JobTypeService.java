package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;
import com.training.ats.exceptions.ErrorMessageBuilder;
import com.training.ats.exceptions.ErrorType;
import com.training.ats.models.Job;
import com.training.ats.models.JobType;
import com.training.ats.models.Level;
import com.training.ats.repositories.JobTypeRepository;
import com.training.ats.dto.JobTypeRecord;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobTypeService implements GenericServiceInterface<JobTypeRecord, Long> {

    private static final String LOGGER = "Job type logger";
    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Override
    public List<JobTypeRecord> get() {
        return jobTypeRepository.findAll()
                .stream()
                .map(jt -> new JobTypeRecord(
                        jt.getJobTypeId(),
                        jt.getType(),
                        jt.getJobLevel().getLevelId(),
                        jt.getJobLevel().getLevel()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public JobTypeRecord get(Long id) {
        Optional<JobType> jobType = jobTypeRepository.findById(id);
        if (jobType.isEmpty())
            throw new EntityNotFoundException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_NOT_FOUND));
        return new JobTypeRecord(jobType.get().getJobTypeId(),
                jobType.get().getType(),
                jobType.get().getJobLevel().getLevelId(),
                jobType.get().getJobLevel().getLevel()
        );
    }

    @Override
    public ResponseRecord delete(Long id) {
        jobTypeRepository.deleteById(id);
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_DELETED));
    }

    @Override
    public ResponseRecord delete() {
        jobTypeRepository.deleteAll();
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_DELETED_ALL));
    }

    @Override
    public ResponseRecord post(JobTypeRecord object, Long id) {
        jobTypeRepository.save(
                JobType.builder()
                        .jobTypeId(id)
                        .jobLevel(Level.builder().levelId(object.jobLevel()).build())
                        .type(object.type())
                        .build()
        );
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_UPDATED));
    }

    @Override
    public ResponseRecord put(JobTypeRecord object) {
        JobType newJobType = JobType.builder()
                .type(object.type())
                .jobLevel(
                        Level.builder()
                                .levelId(object.jobLevel())
                                .build())
                .build();
        jobTypeRepository.save(newJobType);
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_SAVED));
    }
}
