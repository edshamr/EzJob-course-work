package com.example.ezjob.service.impl;

import com.example.ezjob.common.mapper.VacancyMapper;
import com.example.ezjob.exception.CompanyNotFoundException;
import com.example.ezjob.exception.VacancyNotFoundException;
import com.example.ezjob.model.dto.VacancyRequestDto;
import com.example.ezjob.persistense.entity.Vacancy;
import com.example.ezjob.persistense.repository.jpa.VacancyRepository;
import com.example.ezjob.service.CompanyService;
import com.example.ezjob.service.VacancyService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;
    private final CompanyService companyService;

    @Nullable
    @Override
    public List<Vacancy> getAllCompanyVacancies(@Nonnull Long id) {
        final var company = companyService.getCompanyById(id);

        final var vacancies = company.getVacancies();
        return vacancies;
    }

    @Nullable
    @Override
    public Vacancy saveVacancy(@Nonnull VacancyRequestDto requestDto) {
        final var vacancy = vacancyMapper.toVacancy(requestDto);

        final var company = companyService.getCompanyById(requestDto.getCompanyId());
        vacancy.setCompany(company);

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
    public Vacancy updateVacancy(@Nonnull Long vacancyId, @Nonnull VacancyRequestDto requestDto) {
        final var vacancyToUpdate = vacancyRepository.findById(vacancyId).orElseThrow(() -> new CompanyNotFoundException(
                format("Message with id = %d not found.", vacancyId)));
        final var company = companyService.getCompanyById(requestDto.getCompanyId());

        final var vacancy = vacancyMapper.toVacancy(requestDto);
        vacancy.setId(vacancyId);
        vacancy.setCompany(company);

        vacancyMapper.updateVacancy(vacancyToUpdate, vacancy);

        return vacancyRepository.save(vacancyToUpdate);
    }

    @Override
    public void deleteVacancy(@Nonnull Long id) {
        vacancyRepository.deleteById(id);
    }
}
