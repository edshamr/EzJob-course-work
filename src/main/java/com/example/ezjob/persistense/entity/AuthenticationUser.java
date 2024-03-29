package com.example.ezjob.persistense.entity;

import com.example.ezjob.common.ApplicationConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

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

  @Column(name = "user_role", nullable = false)
  @Enumerated(EnumType.STRING)
  private RoleName role;

  @CreatedDate
  @Column(name = "created")
  private ZonedDateTime created;

  @LastModifiedDate
  @Column(name = "updated")
  private ZonedDateTime updated;

  @OneToOne(mappedBy = "authUser")
  private Resume resume;

  @OneToOne(mappedBy = "authUser")
  private Company company;

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
