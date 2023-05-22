package com.example.ezjob.service;

import com.example.ezjob.model.dto.VacancyRequestDto;
import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.entity.Vacancy;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import javax.validation.Valid;
import java.util.List;

public interface VacancyService {

  @Nullable
  List<Vacancy> getAllVacancies(@Nullable String title);
  @Nullable
  List<Resume> getResponsesOnVacancy(@Nonnull Long id);
  @Nullable
  List<Vacancy> getAllCompanyVacancies(@Nonnull Long companyId);
  @Nullable
  Vacancy saveVacancy(@Nonnull @Valid VacancyRequestDto vacancy);
  @Nullable
  Vacancy getVacancyById(@Nonnull Long id);
  @Nonnull
  Vacancy updateVacancy(@Nonnull Long vacancyId, @Nonnull VacancyRequestDto requestDto);
  void deleteVacancy(@Nonnull Long id);
}
