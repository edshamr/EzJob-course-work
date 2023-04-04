package com.example.ezjob.persistense.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

  @ElementCollection
  @Column(name = "skillsList")
  List<String> skillsList;

  @Column(name = "experience")
  int experience;

  @Column(name = "additionalInfo")
  String additionalInfo;
}
