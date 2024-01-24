package com.training.ats.repositories;

import com.training.ats.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * repository for applicant, contains all methods in JpaRepository and BaseRepository
 */
@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, String> {
}
