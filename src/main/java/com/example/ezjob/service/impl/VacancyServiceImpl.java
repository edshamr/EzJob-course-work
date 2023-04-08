package com.example.ezjob.service.impl;

import com.example.ezjob.persistense.entity.Vacancy;
import com.example.ezjob.persistense.repository.VacancyRepository;
import com.example.ezjob.service.VacancyService;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

@Service
public class VacancyServiceImpl implements VacancyService {
  private VacancyRepository vacancyRepository;
  @Override
  public Vacancy saveVacancy(@Nonnull final Vacancy vacancy) {
    return vacancyRepository.save(vacancy);
  }

  @Override
  public Vacancy getVacancyByTitle(@Nonnull final String title) {
    return vacancyRepository.findVacancyByTitle(title);
  }
}
