package com.training.ats.repositories;

import com.training.ats.dto.ApplicationRecord;
import com.training.ats.models.Application;
import com.training.ats.models.AtsUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
  List<Application> findAllByApplicant(AtsUser user);

  @Transactional
  void deleteByApplicationIdAndApplicant(Long id, AtsUser user);

  @Modifying
  @Transactional
  @Query(value = "Update applications set date_of_apply = :#{#app.dateOfApply}, description = :#{#app.description}, " +
          "status_id = :#{#app.statusId}, job_id = :#{#app.jobId} WHERE applicant_id = :username AND " +
          "application_id = :id", nativeQuery = true)
  void updateWhereApplicant(@Param("app") ApplicationRecord app, String username, Long id);
}
