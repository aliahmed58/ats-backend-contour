package com.training.ats.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Levels such as senior, junior, trainee etc.
 */

@Entity
@Table(name = "levels")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Level {
  @Id
  @GeneratedValue
  private Long levelId;

  @NotNull(message = "Level cannot be null")
  @NotBlank(message = "Level cannot be blank")
  private String level;

}
