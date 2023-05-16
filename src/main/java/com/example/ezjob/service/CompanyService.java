package com.example.ezjob.service;

import com.example.ezjob.model.dto.CompanyRequestDto;
import com.example.ezjob.persistense.entity.Company;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import javax.validation.Valid;

public interface CompanyService {
  @Nullable
  Company saveCompany(@Nonnull @Valid Company company);
  @Nullable
  Company getCompanyById(@Nonnull Long id);
  @Nonnull
  Company updateCompany(@Nonnull Long id, @Nonnull CompanyRequestDto requestDto);
  void deleteCompany(@Nonnull Long id);
}
