package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.Vacancy;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface VacancyService {
  @Nullable
  Vacancy saveVacancy(@Nonnull Vacancy vacancy);
  @Nullable
  Vacancy getVacancyByTitle(@Nonnull String title);
}
