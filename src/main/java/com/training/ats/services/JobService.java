package com.training.ats.services;


import com.training.ats.dto.JobTypeRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.exceptions.ErrorMessageBuilder;
import com.training.ats.exceptions.ErrorType;
import com.training.ats.models.Job;
import com.training.ats.models.JobType;
import com.training.ats.models.Level;
import com.training.ats.repositories.JobRepository;
import com.training.ats.dto.JobRecord;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService implements GenericServiceInterface<JobRecord, Long> {

    private static final String LOGGER = "Job Logger";
    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<JobRecord> get() {
        return jobRepository.findAll()
                .stream()
                .map(job -> new JobRecord(
                        job.getJobId(),
                        job.getJobName(),
                        job.getJobDescription(),
                        job.getJobType().getJobTypeId(),
                        job.getJobType().getJobLevel().getLevelId(),
                        new JobTypeRecord(
                                job.getJobType().getJobTypeId(),
                                job.getJobType().getType(),
                                job.getJobType().getJobLevel().getLevelId(),
                                job.getJobType().getJobLevel().getLevel())))
                .collect(Collectors.toList());
    }

    @Override
    public JobRecord get(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty())
            throw new EntityNotFoundException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_NOT_FOUND));
        Job getJob = job.get();
        return new JobRecord(
                getJob.getJobId(),
                getJob.getJobName(),
                getJob.getJobDescription(),
                getJob.getJobType().getJobTypeId(),
                getJob.getJobType().getJobLevel().getLevelId(),
                new JobTypeRecord(
                        getJob.getJobType().getJobTypeId(),
                        getJob.getJobType().getType(),
                        getJob.getJobType().getJobLevel().getLevelId(),
                        getJob.getJobType().getJobLevel().getLevel())
        );
    }

    @Override
    public ResponseRecord delete(Long id) {
        jobRepository.deleteById(id);
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_DELETED));
    }

    @Override
    public ResponseRecord delete() {
        jobRepository.deleteAll();
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_DELETED_ALL));
    }

    @Override
    public ResponseRecord post(JobRecord object, Long id) {
        JobType jobType = JobType.builder()
                .jobTypeId(object.jobTypeId())
                .jobLevel(new Level(object.jobLevelId(), null))
                .build();

        jobRepository.save(
                Job.builder()
                        .jobId(id)
                        .jobType(jobType)
                        .jobName(object.jobName())
                        .jobDescription(object.jobDesc())
                        .build()
        );

        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_UPDATED));
    }

    @Override
    public ResponseRecord put(JobRecord object) {
        JobType jobType = JobType.builder()
                .jobTypeId(object.jobTypeId())
                .jobLevel(new Level(object.jobLevelId(), null))
                .build();

        jobRepository.save(
                Job.builder()
                        .jobType(jobType)
                        .jobName(object.jobName())
                        .jobDescription(object.jobDesc())
                        .build()
        );

        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_SAVED));
    }
}
