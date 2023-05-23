package com.example.ezjob.model.dto;

import com.example.ezjob.persistense.entity.WorkType;
import lombok.Builder;
import lombok.Value;

    @Value
    @Builder
    public class VacancyRequestDto {
        Long companyId;
        String title;
        String country;
        String city;
        WorkType workType;
        String description;
        String additionalInfo;
    }
