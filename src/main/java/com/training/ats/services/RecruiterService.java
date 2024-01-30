package com.training.ats.services;

import com.training.ats.models.AtsUser;
import com.training.ats.models.RoleType;
import com.training.ats.repositories.AtsUserRepository;
import com.training.ats.repositories.StatusRepository;
import com.training.ats.responsedto.RecruiterRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RecruiterService {

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private AtsUserRepository userRepository;

    /**
     * fetch user from auth and return recruiter profile
     * @return RecruiterRecord object
     */
    public RecruiterRecord getRecruiterProfile() {
        AtsUser recruiter = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (recruiter.getRoleType() == RoleType.RECRUITER) {
            return new RecruiterRecord(
                    recruiter.getFirstName(), recruiter.getLastName(), recruiter.getUsername()
            );
        }
        else {
            throw new SecurityException("Unauthorized. Access denied");
        }
    }

}
