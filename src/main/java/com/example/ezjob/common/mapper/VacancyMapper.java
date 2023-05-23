package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.VacancyRequestDto;
import com.example.ezjob.model.dto.VacancyResponseDto;
import com.example.ezjob.persistense.entity.Vacancy;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    Vacancy toVacancy(@Nonnull VacancyRequestDto requestDto);
    @Mapping(target = "companyId", source = "vacancy.company.id")
    VacancyResponseDto toVacancyResponseDto( @Nonnull Vacancy vacancy);
    void updateVacancy(@MappingTarget @Nonnull Vacancy destination, @Nonnull Vacancy source);
}
