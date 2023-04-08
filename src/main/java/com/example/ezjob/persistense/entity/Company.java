package com.example.ezjob.persistense.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  Long id;

  @Column(name = "name")
  String name;

  @Column(name = "stuff_count")
  int stuffCount;
  @Column(name = "description")
  String description;
  @Column(name = "country")
  String country;
  @Column(name = "additional_info")
  String additionalInfo;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
  List<Vacancy> vacancies;
}
