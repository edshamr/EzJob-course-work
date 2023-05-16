package com.example.ezjob.controller;

import com.example.ezjob.common.mapper.VacancyMapper;
import com.example.ezjob.model.dto.VacancyRequestDto;
import com.example.ezjob.model.dto.VacancyResponseDto;
import com.example.ezjob.persistense.entity.Company;
import com.example.ezjob.service.CompanyService;
import com.example.ezjob.service.VacancyService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final VacancyMapper vacancyMapper;
    private final CompanyService companyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyResponseDto> getAllCompanyVacancies(@RequestParam @Nonnull @Min(1) final Long id) {
        final var company = companyService.getCompanyById(id);

        final var vacancies = vacancyService.getAllCompanyVacancies(company);
        final var response = vacancies.stream()
                .map(vacancyMapper::toVacancyResponseDto)
                .toList();

        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyResponseDto createVacancy(@RequestBody @Nonnull VacancyRequestDto requestDto,
                                            @RequestParam @Nonnull Long companyId) {
        final var vacancy = vacancyMapper.toVacancy(requestDto);

        final var company = companyService.getCompanyById(companyId);
        vacancy.setCompany(company);

        final var response = vacancyService.saveVacancy(vacancy);

        return vacancyMapper.toVacancyResponseDto(response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VacancyResponseDto getVacancyById(@PathVariable @Nonnull @Min(1) final Long id) {
        final var response = vacancyService.getVacancyById(id);

        return vacancyMapper.toVacancyResponseDto(response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VacancyResponseDto updateVacancy(@PathVariable @Nonnull @Min(1) final Long id,
                                            @RequestBody @Nonnull @Valid final VacancyRequestDto vacancyRequest) {
        final var response = vacancyService.updateVacancy(id, vacancyRequest);

        return vacancyMapper.toVacancyResponseDto(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteResume(@PathVariable @Nonnull @Min(1) final Long id) {
        vacancyService.deleteVacancy(id);
    }
}
