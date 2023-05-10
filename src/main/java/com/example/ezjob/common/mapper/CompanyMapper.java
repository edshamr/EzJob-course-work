package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.CompanyRequestDto;
import com.example.ezjob.model.dto.CompanyResponseDto;
import com.example.ezjob.persistense.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toCompany(CompanyRequestDto requestDto);
    CompanyResponseDto toCompanyResponseDto(Company requestDto);
    void updateCompany(@MappingTarget Company destination, Company source);
}
