package com.example.ezjob.controller;

import com.example.ezjob.common.mapper.CompanyMapper;
import com.example.ezjob.model.dto.CompanyRequestDto;
import com.example.ezjob.model.dto.CompanyResponseDto;
import com.example.ezjob.service.CompanyService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponseDto createCompany(@Nonnull @Valid final CompanyRequestDto companyRequest) {
        final var response = companyService.saveCompany(companyRequest);
        return companyMapper.toCompanyResponseDto(response);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponseDto getResumeById(@PathVariable @Nonnull @Min(1) final Long id) {
        final var response = companyService.getCompanyById(id);
        return companyMapper.toCompanyResponseDto(response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponseDto updateResume(@PathVariable @Nonnull @Min(1) final Long id,
                                          @RequestBody @Nonnull @Valid final CompanyRequestDto companyRequest) {
        final var response = companyService.updateCompany(id, companyRequest);
        return companyMapper.toCompanyResponseDto(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteResume(@PathVariable @Nonnull @Min(1) final Long id) {
        companyService.deleteCompany(id);
    }
}
