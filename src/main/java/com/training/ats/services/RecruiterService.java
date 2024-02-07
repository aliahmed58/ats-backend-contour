package com.training.ats.services;

import com.training.ats.dto.AtsUserRecord;
import com.training.ats.dto.ResponseRecord;
import com.training.ats.models.AtsUser;
import com.training.ats.models.RoleType;
import com.training.ats.repositories.AtsUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecruiterService implements GenericServiceInterface<AtsUserRecord, String> {
    private final static String LOGGER = "Recruiter logger";
    @Autowired
    private AtsUserRepository recruiterRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<AtsUserRecord> get() {
        return recruiterRepository.findAllByRoleType(RoleType.APPLICANT)
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
        Optional<AtsUser> applicant = recruiterRepository.findByUsernameAndRoleType(id, RoleType.RECRUITER);
        if (applicant.isEmpty())
            throw new EntityNotFoundException("Recruiter not found");
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
        recruiterRepository.deleteById(id);
        return new ResponseRecord(HttpStatus.OK.value(), "Recruiter deleted");
    }

    @Override
    public ResponseRecord delete() {
        recruiterRepository.deleteAllByRoleType(RoleType.RECRUITER);
        return new ResponseRecord(HttpStatus.OK.value(), "All recruiters deleted");
    }

    @Override
    public ResponseRecord post(AtsUserRecord object, String id) {
        // get current user to get its role type
        AtsUser user = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (!user.getUsername().equals(id)) {
                throw new AuthenticationException("Unauthorized update");
            }
            recruiterRepository.save(
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
        // create a new recruiter
        // default password test123
        recruiterRepository.save(
                AtsUser.builder()
                        .username(object.username())
                        .firstName(object.firstname())
                        .lastName(object.lastname())
                        .roleType(RoleType.RECRUITER)
                        .passwordHash(passwordEncoder.encode("test123"))
                        .build()
        );
        return new ResponseRecord(HttpStatus.OK.value(), "New Recruiter saved");
    }
}
