package com.training.ats.models;

import ch.qos.logback.core.status.StatusUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * application status: pending, rejected, etc.
 */
@Entity
public class Status {
  @Id
  @GeneratedValue
  private long statusId;

  @NotNull(message = "Status cannot be null")
  @NotBlank(message = "Status cannot be blank")
  @NotEmpty(message = "Status cannot be empty")
  private String statusType;

  public Status(long statusId, String statusType) {
    this.statusId = statusId;
    this.statusType = statusType;
  }

  public Status() {}

  public long getStatusId() {
    return statusId;
  }

  public void setStatusId(long statusId) {
    this.statusId = statusId;
  }

  public String getStatusType() {
    return statusType;
  }

  public void setStatusType(String statusType) {
    this.statusType = statusType;
  }

  // equate status to use id
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Status status = (Status) o;
    return statusId == status.statusId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusId);
  }
}
