package com.example.ezjob.model.dto;

import com.example.ezjob.persistense.entity.WorkType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VacancyResponseDto {
        Long companyId;
        String title;
        String country;
        String city;
        String description;
        String additionalInfo;
}
