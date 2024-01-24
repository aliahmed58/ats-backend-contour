package com.training.ats.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * Levels such as senior, junior, trainee etc.
 */

@Entity
@Table(name = "levels")
public class Level {
  @Id
  @GeneratedValue
  private Long levelId;

  @NotNull(message = "Level cannot be null")
  @NotBlank(message = "Level cannot be blank")
  private String level;

  public Level() {
  }

  public Level(Long levelId, String level) {
    this.levelId = levelId;
    this.level = level;
  }

  public Long getLevelId() {
    return levelId;
  }

  public void setLevelId(Long levelId) {
    this.levelId = levelId;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  // equate levels on their id
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Level level = (Level) o;
    return Objects.equals(levelId, level.levelId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(levelId);
  }
}
