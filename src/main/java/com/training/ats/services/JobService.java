package com.training.ats.services;


import com.training.ats.dto.JobTypeRecord;
import com.training.ats.dto.ResponseRecord;
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

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<JobRecord> getAll() {
        return jobRepository.findAll()
                .stream()
                .map(job -> new JobRecord(
                        job.getJobName(),
                        job.getJobDescription(),
                        job.getJobType().getJobTypeId(),
                        job.getJobType().getJobLevel().getLevelId(),
                        new JobTypeRecord(job.getJobType().getType(), job.getJobType().getJobLevel().getLevelId())))
                .collect(Collectors.toList());
    }

    @Override
    public JobRecord getById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty())
            throw new EntityNotFoundException("Job entity not found with id " + id);
        Job getJob = job.get();
        return new JobRecord(
                getJob.getJobName(),
                getJob.getJobDescription(),
                getJob.getJobType().getJobTypeId(),
                getJob.getJobType().getJobLevel().getLevelId(),
                new JobTypeRecord(getJob.getJobType().getType(), getJob.getJobType().getJobLevel().getLevelId())
        );
    }

    @Override
    public ResponseRecord deleteById(Long id) {
        jobRepository.deleteById(id);
        return new ResponseRecord(HttpStatus.OK.value(), "Job record deleted");
    }

    @Override
    public ResponseRecord deleteAll() {
        jobRepository.deleteAll();
        return new ResponseRecord(HttpStatus.OK.value(), "All jobs deleted");
    }

    @Override
    public ResponseRecord update(JobRecord object, Long id) {
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

        return new ResponseRecord(HttpStatus.OK.value(), "Job updated");
    }

    @Override
    public ResponseRecord save(JobRecord object) {
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

        return new ResponseRecord(HttpStatus.OK.value(), "Job saved");
    }
}
