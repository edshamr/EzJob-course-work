package com.example.ezjob.model.dto;

import com.example.ezjob.persistense.entity.Company;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VacancyRequestDto {
    String title;
    String description;
    String additionalInfo;
//    Company company;
}
