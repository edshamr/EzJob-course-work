package com.example.ezjob.persistense.entity;

import com.example.ezjob.common.ApplicationConstants;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@ToString
@Table(name = "auth_users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = ApplicationConstants.FieldsConstraints.MAX_LOGIN_SIZE,
          min = ApplicationConstants.FieldsConstraints.MIN_LOGIN_SIZE)
  @NotBlank
  @NotEmpty
  @Column(name = "username")
  private String username;

  @Size(max = ApplicationConstants.FieldsConstraints.MAX_LOGIN_SIZE,
          min = ApplicationConstants.FieldsConstraints.MIN_LOGIN_SIZE)
  @NotBlank
  @NotEmpty
  @Column(name = "password")
  private String password;

  @Size(max = ApplicationConstants.FieldsConstraints.MAX_PASSWORD_SIZE,
          min = ApplicationConstants.FieldsConstraints.MIN_PASSWORD_SIZE)
  @NotBlank
  @NotEmpty
  @Column(name = "email")
  private String email;

  @ElementCollection(targetClass = RoleName.class, fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "user_role", nullable = false)
  @Enumerated(EnumType.STRING)
  Set<RoleName> roles;

  @CreatedDate
  @Column(name = "created")
  private ZonedDateTime created;

  @LastModifiedDate
  @Column(name = "updated")
  private ZonedDateTime updated;

  @PrePersist
  private void setDates() {
    created = ZonedDateTime.now();
    updated = ZonedDateTime.now();
  }

  @PreUpdate
  private void updateDates() {
    updated = ZonedDateTime.now();
  }
}
