package com.training.ats.repositories;

import com.training.ats.models.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

    @Modifying
    @Query("update Level l set l.level = ?1 where l.levelId = ?2")
    void updateLevelById(String level, Long id);
}
