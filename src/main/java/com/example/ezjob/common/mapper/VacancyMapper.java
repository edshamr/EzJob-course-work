package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.VacancyRequestDto;
import com.example.ezjob.model.dto.VacancyResponseDto;
import com.example.ezjob.persistense.entity.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    Vacancy toVacancy(VacancyRequestDto requestDto);
    VacancyResponseDto toVacancyResponseDto(Vacancy requestDto);
    void updateVacancy(@MappingTarget Vacancy destination, Vacancy source);
}
