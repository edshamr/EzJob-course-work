package com.example.ezjob.persistense.repository.jpa;

import com.example.ezjob.persistense.entity.Company;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
  Company findCompanyByName(@Nonnull String name);
}
