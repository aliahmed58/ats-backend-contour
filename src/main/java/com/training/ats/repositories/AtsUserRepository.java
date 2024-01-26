package com.training.ats.repositories;

import com.training.ats.models.AtsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtsUserRepository extends JpaRepository<AtsUser, String> {
}
