package com.example.ezjob.service.impl;

import com.example.ezjob.persistense.entity.Company;
import com.example.ezjob.persistense.repository.jpa.CompanyRepository;
import com.example.ezjob.service.CompanyService;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
  private CompanyRepository companyRepository;

  @Override
  public Company saveCompany(@Nonnull final Company company) {
    return companyRepository.save(company);
  }

  @Override
  public Company getCompanyByName(@Nonnull final String name) {
    return companyRepository.findCompanyByName(name);
  }
}
