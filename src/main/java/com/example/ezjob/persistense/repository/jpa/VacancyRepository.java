package com.example.ezjob.persistense.repository.jpa;

import com.example.ezjob.persistense.entity.Vacancy;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
  Vacancy findVacancyByTitle(@Nonnull String name);
}
