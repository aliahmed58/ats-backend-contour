package com.training.ats.services;

import com.training.ats.dto.AtsUserRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.models.AtsUser;
import com.training.ats.models.RoleType;
import com.training.ats.repositories.AtsUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service for applicants containing all the business logic that will be used in the controller
 */
@Service
@Transactional
public class ApplicantService implements GenericServiceInterface<AtsUserRecord, String> {


    @Autowired
    private AtsUserRepository applicantRepository;

    @Override
    public List<AtsUserRecord> get() {
        return applicantRepository.findAllByRoleType(RoleType.APPLICANT)
                .stream()
                .map(user -> new AtsUserRecord(user.getFirstName(), user.getLastName(), user.getUsername()))
                .collect(Collectors.toList());
    }

    @Override
    public AtsUserRecord get(String id) {
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getUsername().equals(id)) {
            throw new EntityNotFoundException("Cannot get entity of unauthorized user");
        }
        Optional<AtsUser> applicant = applicantRepository.findByUsernameAndRoleType(id, RoleType.APPLICANT);
        if (applicant.isEmpty())
            throw new EntityNotFoundException("user not found");
        return new AtsUserRecord(
                applicant.get().getFirstName(), applicant.get().getLastName(), applicant.get().getUsername()
        );
    }

    @Override
    public ResponseRecord delete(String id) {
        // verify if the delete operation is from the authenticated user
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.getUsername().equals(id)) {
            return new ResponseRecord(HttpStatus.UNAUTHORIZED.value(), "Unauthorized delete operation");
        }
        applicantRepository.deleteById(id);
        return new ResponseRecord(HttpStatus.OK.value(), "Applicant deleted");
    }

    @Override
    public ResponseRecord delete() {
        applicantRepository.deleteAllByRoleType(RoleType.APPLICANT);
        return new ResponseRecord(HttpStatus.OK.value(), "All applicants deleted");
    }

    @Override
    public ResponseRecord post(AtsUserRecord object, String id) {
        // get current user to get its role type
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (!user.getUsername().equals(id)) {
                throw new AuthenticationException("Unauthorized update");
            }
            applicantRepository.save(
                    AtsUser.builder()
                            .username(id)
                            .firstName(object.firstname())
                            .lastName(object.lastname())
                            .build()
            );
        } catch (AuthenticationException ignored) {
            return new ResponseRecord(HttpStatus.UNAUTHORIZED.value(), "Unauthorized update");
        }

        return new ResponseRecord(HttpStatus.OK.value(), "User updated");
    }

    @Override
    public ResponseRecord put(AtsUserRecord object) {
        // users are saved thru register api endpoint rather than save method
        return null;
    }
}
