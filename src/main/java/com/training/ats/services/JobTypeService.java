package com.training.ats.services;

import com.training.ats.dto.ResponseRecord;
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

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Override
    public List<JobTypeRecord> getAll() {
        return jobTypeRepository.findAll()
                .stream()
                .map(jt -> new JobTypeRecord(
                        jt.getType(),
                        jt.getJobLevel().getLevelId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public JobTypeRecord getById(Long id) {
        Optional<JobType> jobType = jobTypeRepository.findById(id);
        if (jobType.isEmpty())
            throw new EntityNotFoundException("Job type not found with id " + id);
        return new JobTypeRecord(
                jobType.get().getType(),
                jobType.get().getJobLevel().getLevelId()
        );
    }

    @Override
    public ResponseRecord deleteById(Long id) {
        jobTypeRepository.deleteById(id);
        return new ResponseRecord(HttpStatus.OK.value(), "Job Type deleted");
    }

    @Override
    public ResponseRecord deleteAll() {
        jobTypeRepository.deleteAll();
        return new ResponseRecord(HttpStatus.OK.value(), "All job types deleted");
    }

    @Override
    public ResponseRecord update(JobTypeRecord object, Long id) {
        jobTypeRepository.save(
                JobType.builder()
                        .jobTypeId(id)
                        .jobLevel(Level.builder().levelId(object.jobLevel()).build())
                        .type(object.type())
                        .build()
        );
        return new ResponseRecord(HttpStatus.OK.value(), "Job Type updated");
    }

    @Override
    public ResponseRecord save(JobTypeRecord object) {
        JobType newJobType = JobType.builder()
                .type(object.type())
                .jobLevel(
                        Level.builder()
                                .levelId(object.jobLevel())
                                .build())
                .build();
        jobTypeRepository.save(newJobType);
        return new ResponseRecord(HttpStatus.OK.value(), "Job type saved");
    }
}
