package com.training.ats.services;

import com.training.ats.dto.AllApplicationRecord;
import com.training.ats.dto.AtsUserRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.exceptions.ErrorMessageBuilder;
import com.training.ats.exceptions.ErrorType;
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
    private static final String LOGGER = "Application Logger";
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<ApplicationRecord> get() {
        return applicationRepository.findAll()
                .stream()
                .map(app -> new ApplicationRecord(app.getDateOfApply(), app.getDescription(),
                        app.getApplicationStatus().getStatusId(), app.getJob().getJobId(),
                        app.getApplicant().getUsername()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationRecord get(Long id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isEmpty()) {
            throw new EntityNotFoundException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_NOT_FOUND));
        }
        Application a = application.get();
        // only return if the application is of the authenticated user
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getUsername().equals(a.getApplicant().getUsername())) {
            throw new EntityNotFoundException(ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_NOT_FOUND));
        }
        return new ApplicationRecord(a.getDateOfApply(), a.getDescription(),
                a.getApplicationStatus().getStatusId(), a.getJob().getJobId(),
                a.getApplicant().getUsername()
        );
    }

    @Override
    public ResponseRecord delete(Long id) {
        // delete only if the application is of us
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        applicationRepository.deleteByApplicationIdAndApplicant(id, user);
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_DELETED));
    }

    @Override
    public ResponseRecord delete() {
        applicationRepository.deleteAll();
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_DELETED_ALL));
    }

    @Override
    public ResponseRecord post(ApplicationRecord object, Long id) {

        // only updated if the authenticated user is making a request
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getUsername().equals(object.applicantId())) {
            return new ResponseRecord(HttpStatus.UNAUTHORIZED.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.UNAUTHORIZED_OPERATION));
        }

        // update with username fetched from authenticated user object so other users cannot change
        // the application of anyone other than themselves
        applicationRepository.updateWhereApplicant(
                object, user.getUsername(), id);
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_UPDATED));
    }

    @Override
    public ResponseRecord put(ApplicationRecord object) {
        // only save if authenticated user is saving
        // only updated if the authenticated user is making a request
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getUsername().equals(object.applicantId())) {
            return new ResponseRecord(HttpStatus.UNAUTHORIZED.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.UNAUTHORIZED_OPERATION));
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
        return new ResponseRecord(HttpStatus.OK.value(), ErrorMessageBuilder.getMessage(LOGGER, ErrorType.ENTITY_SAVED));
    }

    public List<AllApplicationRecord> getApplicationsForRecruiter() {
        List<Application> applications = applicationRepository.findAll();

        return applications.stream().map(application -> new AllApplicationRecord(
                application.getApplicationId(),
                application.getApplicant().getUsername(),
                application.getJob().getJobName(),
                application.getJob().getJobType().getJobLevel().getLevel(),
                application.getApplicationStatus().getStatusType(),
                application.getDescription()
        )).toList();
    }
}
