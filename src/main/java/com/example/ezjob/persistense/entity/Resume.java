package com.example.ezjob.persistense.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Representation of employee resume
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

  @Column(name = "additional_info", columnDefinition = "TEXT")
  String additionalInfo;

  @OneToOne
  @JoinColumn(name = "auth_user_id")
  @ToString.Exclude
  private AuthenticationUser authUser;

  @ManyToMany
  @ToString.Exclude
  @JoinTable(
          name = "resume_vacancy",
          joinColumns = @JoinColumn(name = "resume_id"),
          inverseJoinColumns = @JoinColumn(name = "vacancy_id")
  )
  List<Vacancy> vacancies;
}
