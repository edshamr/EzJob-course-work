package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VacancyResponseDto {
        Long id;
        String title;
        String description;
        String additionalInfo;
}
