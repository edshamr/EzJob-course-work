package com.example.ezjob.persistense.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "vacancy")
public class Vacancy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "title")
  String title;

  @Column(name = "country")
  String country;

  @Column(name = "city")
  String city;

  @Enumerated(EnumType.STRING)
  WorkType workType;

  @Column(name = "description")
  String description;

  @Column(name = "additional_info")
  String additionalInfo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  @ToString.Exclude
  Company company;

  @ManyToMany(mappedBy = "vacancies")
  List<Resume> resumes;
}
