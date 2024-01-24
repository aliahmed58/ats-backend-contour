package com.training.ats.repositories;

import com.training.ats.models.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * repository for recruiter same as an applicant
 */
@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, String> {
}
