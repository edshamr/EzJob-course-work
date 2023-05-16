package com.example.ezjob.service;

import com.example.ezjob.model.dto.VacancyRequestDto;
import com.example.ezjob.persistense.entity.Company;
import com.example.ezjob.persistense.entity.Vacancy;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import javax.validation.Valid;
import java.util.List;

public interface VacancyService {
  @Nullable
  List<Vacancy> getAllCompanyVacancies(@Nonnull Company company);
  @Nullable
  Vacancy saveVacancy(@Nonnull @Valid Vacancy vacancy);
  @Nullable
  Vacancy getVacancyById(@Nonnull Long id);
  @Nonnull
  Vacancy updateVacancy(@Nonnull Long id, @Nonnull VacancyRequestDto requestDto);
  void deleteVacancy(@Nonnull Long id);
}
