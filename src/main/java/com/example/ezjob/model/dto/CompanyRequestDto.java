package com.example.ezjob.model.dto;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CompanyRequestDto {
    @Nonnull
    String name;
    @Nonnull
    int stuffCount;
    @Nonnull
    String description;
    @Nonnull
    String country;
    @Nullable
    String additionalInfo;
}
