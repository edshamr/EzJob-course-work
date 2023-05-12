package com.example.ezjob.service.impl;

import com.example.ezjob.common.mapper.VacancyMapper;
import com.example.ezjob.exception.CompanyNotFoundException;
import com.example.ezjob.exception.VacancyNotFoundException;
import com.example.ezjob.model.dto.VacancyRequestDto;
import com.example.ezjob.persistense.entity.Vacancy;
import com.example.ezjob.persistense.repository.jpa.VacancyRepository;
import com.example.ezjob.service.VacancyService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class VacancyServiceImpl implements VacancyService {
    private VacancyRepository vacancyRepository;
    private VacancyMapper vacancyMapper;

    @Nullable
    @Override
    public Vacancy saveVacancy(@Nonnull VacancyRequestDto requestDto) {
        final var vacancy = vacancyMapper.toVacancy(requestDto);
        return vacancyRepository.save(vacancy);
    }

    @Nullable
    @Override
    public Vacancy getVacancyById(@Nonnull Long id) {
        return vacancyRepository.findById(id).orElseThrow(() -> new VacancyNotFoundException(
                format("Message with id = %d not found.", id)));
    }

    @Nonnull
    @Override
    public Vacancy updateVacancy(@Nonnull Long id, @Nonnull VacancyRequestDto requestDto) {
        final var companyToUpdate = vacancyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(
                format("Message with id = %d not found.", id)));
        final var company = vacancyMapper.toVacancy(requestDto);

        vacancyMapper.updateVacancy(companyToUpdate, company);

        return vacancyRepository.save(companyToUpdate);
    }

    @Override
    public void deleteVacancy(@Nonnull Long id) {
        vacancyRepository.deleteById(id);
    }
}
