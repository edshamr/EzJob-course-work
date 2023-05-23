package com.example.ezjob.controller;

import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.common.mapper.VacancyMapper;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.model.dto.VacancyRequestDto;
import com.example.ezjob.model.dto.VacancyResponseDto;
import com.example.ezjob.service.VacancyService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final VacancyMapper vacancyMapper;
    private final ResumeMapper resumeMapper;

    @GetMapping("/company")
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyResponseDto> getAllCompanyVacancies(@RequestParam @Nonnull @Min(1) final Long companyId) {
        final var vacancies = vacancyService.getAllCompanyVacancies(companyId);

        final var response = vacancies.stream()
                .map(vacancyMapper::toVacancyResponseDto)
                .toList();

        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VacancyResponseDto> getVacancies(@RequestParam @Nullable String title,
                                                 @RequestParam @Nullable String country,
                                                 @RequestParam @Nullable String city) {
        final var vacancies = vacancyService.getAllVacancies(title, country, city);

        final var response = vacancies.stream()
                .map(vacancyMapper::toVacancyResponseDto)
                .toList();

        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyResponseDto createVacancy(@RequestBody @Nonnull VacancyRequestDto requestDto) {
        final var response = vacancyService.saveVacancy(requestDto);
        return vacancyMapper.toVacancyResponseDto(response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VacancyResponseDto getVacancyById(@PathVariable @Nonnull @Min(1) final Long id) {
        final var response = vacancyService.getVacancyById(id);

        return vacancyMapper.toVacancyResponseDto(response);
    }

    @GetMapping("/{id}/responses")
    @ResponseStatus(HttpStatus.OK)
    public List<ResumeResponseDto> getResponsesOnVacancy(@PathVariable @Nonnull @Min(1) final Long id) {
        final var resumes = vacancyService.getResponsesOnVacancy(id);

        final var response = resumes.stream()
                .map(resumeMapper::toResumeResponseDto)
                .collect(Collectors.toSet());
        return response.stream().toList();
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
