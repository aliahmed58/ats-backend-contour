package com.training.ats.repositories;

import com.training.ats.models.AtsUser;
import com.training.ats.models.RoleType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtsUserRepository extends JpaRepository<AtsUser, String> {

    List<AtsUser> findAllByRoleType(RoleType roleType);

    Optional<AtsUser> findByUsernameAndRoleType(String username, RoleType roleType);

    @Transactional
    void deleteAllByRoleType(RoleType roleType);
}
