package com.example.ezjob.service.impl;

import com.example.ezjob.common.mapper.CompanyMapper;
import com.example.ezjob.exception.CompanyNotFoundException;
import com.example.ezjob.model.dto.CompanyRequestDto;
import com.example.ezjob.persistense.entity.Company;
import com.example.ezjob.persistense.repository.jpa.CompanyRepository;
import com.example.ezjob.service.CompanyService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    @Transactional
    public Company saveCompany(@Nonnull @Valid Company company) {
        return companyRepository.save(company);
    }

    @Nullable
    @Override
    public Company getCompanyById(@Nonnull Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(
                format("Company with id = %d not found.", id)));
    }

    @Nonnull
    @Override
    @Transactional
    public Company updateCompany(@Nonnull Long id, @Nonnull CompanyRequestDto requestDto) {
        final var companyToUpdate = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(
                format("Message with id = %d not found.", id)));
        final var company = companyMapper.toCompany(requestDto);
        company.setId(id);

        companyMapper.updateCompany(companyToUpdate, company);

        return companyRepository.save(companyToUpdate);
    }

    @Override
    @Transactional
    public void deleteCompany(@Nonnull Long id) {
        companyRepository.deleteById(id);
    }
}
