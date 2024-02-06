package com.training.ats.models;

import ch.qos.logback.core.status.StatusUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.util.Objects;

/**
 * application status: pending, rejected, etc.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Status implements Persistable<Long> {
  @Id
  @GeneratedValue
  private Long statusId;

  @NotNull(message = "Status cannot be null")
  @NotBlank(message = "Status cannot be blank")
  private String statusType;

  @Override
  public Long getId() {
    return this.statusId;
  }

  @Override
  public boolean isNew() {
    return this.statusId == null;
  }
}
