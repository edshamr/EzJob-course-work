package com.example.ezjob.service;

import com.example.ezjob.model.dto.VacancyRequestDto;
import com.example.ezjob.persistense.entity.Vacancy;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import javax.validation.Valid;

public interface VacancyService {
  @Nullable
  Vacancy saveVacancy(@Nonnull @Valid VacancyRequestDto requestDto);
  @Nullable
  Vacancy getVacancyById(@Nonnull Long id);
  @Nonnull
  Vacancy updateVacancy(@Nonnull Long id, @Nonnull VacancyRequestDto requestDto);
  void deleteVacancy(@Nonnull Long id);
}
