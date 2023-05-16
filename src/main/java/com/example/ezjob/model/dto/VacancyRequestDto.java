package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VacancyRequestDto {
    String title;
    String description;
    String additionalInfo;
}
