package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.Company;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface CompanyService {
  @Nullable
  Company saveCompany(@Nonnull Company company);
  @Nullable
  Company getCompanyByName(@Nonnull String name);
}
