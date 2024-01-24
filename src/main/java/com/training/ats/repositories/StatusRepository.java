package com.training.ats.repositories;

import com.training.ats.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * status repository to do operations on status table
 */
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
