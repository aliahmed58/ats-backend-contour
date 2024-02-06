package com.training.ats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.util.Objects;

/**
 * job type table including type of job and level of seniority
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobType implements Persistable<Long> {

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

  @Override
  public Long getId() {
    return this.jobTypeId;
  }

  @Override
  public boolean isNew() {
    return this.jobTypeId == null;
  }
}
