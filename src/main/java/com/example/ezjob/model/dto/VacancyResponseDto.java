package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VacancyResponseDto {
        Long id;
        Long companyId;
        String title;
        String city;
        String description;
        String additionalInfo;
}
