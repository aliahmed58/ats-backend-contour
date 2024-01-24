package com.training.ats.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * a user (applicant, recruiter) will inherit from this BaseUser class which has common information
 * required in both the classes
 */
// @Inheritance annotation tells that classes inherit from this class.
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseUser {

  // the primary key will be the String username
  @Id
  protected String username;
  @NotNull(message = "First name cannot be null")
  @NotEmpty(message = "First name cannot be empty")
  @NotBlank(message = "First name cannot be blank")
  protected String firstName;

  @NotNull(message = "Last name cannot be null")
  @NotEmpty(message = "Last name cannot be empty")
  @NotBlank(message = "Last name cannot be blank")
  protected String lastName;

  /*
   * default constructors, getters and setters
   */

  public BaseUser(String username, String firstName, String lastName) {
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public BaseUser() {}

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  // Override equals and hash function to equate users on username
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseUser baseUser = (BaseUser) o;
    return Objects.equals(username, baseUser.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username);
  }
}
