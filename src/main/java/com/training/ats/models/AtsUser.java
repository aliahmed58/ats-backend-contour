package com.training.ats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * a user (applicant, recruiter) will inherit from this BaseUser class which has common information
 * required in both the classes
 */
// @Inheritance annotation tells that classes inherit from this class.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class AtsUser implements UserDetails {

  // the primary key will be the String username
  @Id
  protected String username;
  @NotNull(message = "First name cannot be null")
  @NotBlank(message = "First name cannot be blank")
  protected String firstName;

  @NotNull(message = "Last name cannot be null")
  @NotBlank(message = "Last name cannot be blank")
  protected String lastName;

  @NotNull(message = "password hash cannot be null")
  protected String passwordHash;

  @Enumerated(value = EnumType.ORDINAL)
  protected RoleType roleType;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return passwordHash;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
