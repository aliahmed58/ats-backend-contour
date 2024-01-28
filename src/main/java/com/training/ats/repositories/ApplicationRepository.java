package com.training.ats.repositories;

import com.training.ats.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
  List<Application> findByApplicant(User user);
}
