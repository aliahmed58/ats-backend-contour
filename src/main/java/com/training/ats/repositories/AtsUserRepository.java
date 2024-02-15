package com.training.ats.repositories;

import com.training.ats.dto.StatusCount;
import com.training.ats.models.AtsUser;
import com.training.ats.models.RoleType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtsUserRepository extends JpaRepository<AtsUser, String> {

    List<AtsUser> findAllByRoleType(RoleType roleType);

    Optional<AtsUser> findByUsernameAndRoleType(String username, RoleType roleType);

    @Transactional
    void deleteAllByRoleType(RoleType roleType);

    @Query(value = "select s.statusType as statusType, count(s.statusType) as statusCount " +
            "from Application a left join Status s on s.statusId = a.applicationStatus.statusId where a.applicant = :user " +
            "group by s.statusType")
    List<StatusCount> countStatusTypeByUserInterface(AtsUser user);
}
