package com.training.ats.services;

import com.training.ats.dto.AtsUserRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.models.Application;
import com.training.ats.models.AtsUser;
import com.training.ats.models.Job;
import com.training.ats.models.Status;
import com.training.ats.repositories.ApplicationRepository;
import com.training.ats.dto.ApplicationRecord;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationService implements GenericServiceInterface<ApplicationRecord, Long> {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<ApplicationRecord> getAll() {
        return applicationRepository.findAll()
                .stream()
                .map(app -> new ApplicationRecord(
                        app.getApplicationId(), app.getDateOfApply(), app.getDescription(),
                        app.getApplicationStatus().getStatusId(), app.getJob().getJobId(),
                        app.getApplicant().getUsername()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationRecord getById(Long id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isEmpty()) {
            throw new EntityNotFoundException("Application not found");
        }
        Application a = application.get();
        // only return if the application is of the authenticated user
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getUsername().equals(a.getApplicant().getUsername())) {
            throw new EntityNotFoundException("Application not found");
        }
        return new ApplicationRecord(
                a.getApplicationId(), a.getDateOfApply(), a.getDescription(),
                a.getApplicationStatus().getStatusId(), a.getJob().getJobId(),
                a.getApplicant().getUsername()
        );
    }

    @Override
    public ResponseRecord deleteById(Long id) {
        // delete only if the application is of us
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        applicationRepository.deleteByApplicationIdAndApplicant(id, user);
        return new ResponseRecord(HttpStatus.OK.value(), "Application record deleted");
    }

    @Override
    public ResponseRecord deleteAll() {
        applicationRepository.deleteAll();
        return new ResponseRecord(HttpStatus.OK.value(), "Application records deleted");
    }

    @Override
    public ResponseRecord update(ApplicationRecord object, Long id) {

        // only updated if the authenticated user is making a request
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getUsername().equals(object.applicantId())) {
            return new ResponseRecord(HttpStatus.UNAUTHORIZED.value(), "Unauthorized update");
        }

        applicationRepository.updateWhereApplicant(
                object, user.getUsername(), id);
        return new ResponseRecord(HttpStatus.OK.value(), "Application updated");
    }

    @Override
    public ResponseRecord save(ApplicationRecord object) {
        // only save if authenticated user is saving
        // only updated if the authenticated user is making a request
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getUsername().equals(object.applicantId())) {
            return new ResponseRecord(HttpStatus.UNAUTHORIZED.value(), "Unauthorized save");
        }

        applicationRepository.save(
                Application.builder()
                        .description(object.description())
                        .applicationStatus(Status.builder().statusId(object.statusId()).build())
                        .dateOfApply(object.dateOfApply())
                        .job(Job.builder().jobId(object.jobId()).build())
                        .applicant(AtsUser.builder().username(object.applicantId()).build())
                        .build()
        );
        return new ResponseRecord(HttpStatus.OK.value(), "Application Saved");
    }
}
