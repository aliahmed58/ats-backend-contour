package com.training.ats.services;

import com.training.ats.models.AtsUser;
import com.training.ats.repositories.AtsUserRepository;
import com.training.ats.repositories.JobRepository;
import com.training.ats.requestdto.AddJobRecord;
import com.training.ats.responsedto.RecruiterRecord;
import com.training.ats.responsedto.ResponseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RecruiterService {

    @Autowired
    private AtsUserRepository userRepository;
    @Autowired
    private JobRepository jobRepository;


    /**
     * fetch user from auth and return recruiter profile
     *
     * @return RecruiterRecord object
     */
    public RecruiterRecord getRecruiterProfile() {
        AtsUser recruiter = (AtsUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new RecruiterRecord(
                recruiter.getFirstName(), recruiter.getLastName(), recruiter.getUsername()
        );
    }

    public ResponseRecord addNewJob(AddJobRecord newJobRecord) {
        // verify if the request was made from a recruiter user
        return null;
    }

    private boolean verifyUser(AtsUser user) {
        return false;
    }

}
