package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.CompanyRequestDto;
import com.example.ezjob.model.dto.CompanyResponseDto;
import com.example.ezjob.persistense.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toCompany(CompanyRequestDto requestDto);
    @Mapping(source = "id", target = "companyId")
    CompanyResponseDto toCompanyResponseDto(Company requestDto);
    @Mapping(target = "authUser", ignore = true)
    void updateCompany(@MappingTarget Company destination, Company source);
}
