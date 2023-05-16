package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CompanyResponseDto {
    Long companyId;
    String name;
    int stuffCount;
    String description;
    String country;
    String additionalInfo;
}
