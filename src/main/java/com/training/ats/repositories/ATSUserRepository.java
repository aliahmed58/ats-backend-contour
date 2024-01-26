package com.training.ats.repositories;

import com.training.ats.models.ATSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ATSUserRepository extends JpaRepository<ATSUser, String> {
}
