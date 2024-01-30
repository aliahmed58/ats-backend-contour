package com.training.ats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * job type table including type of job and level of seniority
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobType {

  @Id
  @GeneratedValue
  private Long jobTypeId;

  @NotNull(message = "Job type cannot be null")
  @NotBlank(message = "Job type cannot be blank")
  private String type;

  @NotNull(message = "Job level cannot be null")
  @ManyToOne
  @JoinColumn(name = "level_id", nullable = false)
  private Level jobLevel;

}
