package com.training.ats.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * a user (applicant, recruiter) will inherit from this BaseUser class which has common information
 * required in both the classes
 */
// @Inheritance annotation tells that classes inherit from this class.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class AtsUser implements UserDetails {

  @Transient
  private final String ROLE_PREFIX = "ROLE_";
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

  @Enumerated(value = EnumType.STRING)
  protected RoleType roleType;

  @OneToMany(mappedBy = "applicant", cascade = CascadeType.REMOVE)
  private List<Application> applications;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorityList = new ArrayList<>();
    authorityList.add(new SimpleGrantedAuthority(ROLE_PREFIX + roleType));
    return authorityList;
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
