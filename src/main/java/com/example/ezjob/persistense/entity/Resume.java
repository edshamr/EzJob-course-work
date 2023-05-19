package com.example.ezjob.persistense.entity;

import com.example.ezjob.common.mapper.AuthenticationUserMapper;
import jakarta.persistence.*;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Representation of employee resume
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "resume")
public class Resume {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "email")
  String email;

  @Column(name = "firstname")
  String firstname;

  @Column(name = "lastname")
  String lastname;

  @Column(name = "patronymic")
  String patronymic;

  @Column(name = "city")
  String city;

  @Column(name = "country")
  String country;

  @Column(name = "phone")
  String phone;

  @Column(name = "position")
  String position;

  @Column(name = "university")
  String university;

  @Column(name = "skills", columnDefinition = "TEXT")
  String skills;

  @Column(name = "experience")
  int experience;

  @Column(name = "additionalInfo", columnDefinition = "TEXT")
  String additionalInfo;

  @OneToOne
  @JoinColumn(name = "auth_users_id")
  @ToString.Exclude
  private AuthenticationUser authUser;

  @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
  List<Vacancy> vacancies;
}
