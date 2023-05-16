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

  @OneToOne
  @JoinColumn(name = "auth_users_id")
  @ToString.Exclude
  private AuthenticationUser authUser;
}
