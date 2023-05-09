package com.example.ezjob.persistense.entity;

import com.example.ezjob.common.ApplicationConstants;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@ToString
@Table(name = "auth_users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@RedisHash("AuthenticationUser")
public class AuthenticationUser implements Serializable {
  @Serial
  private static final long serialVersionUID = 5351684144827955888L;
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

  @OneToOne(mappedBy = "authUser")
  private Resume resume;

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
