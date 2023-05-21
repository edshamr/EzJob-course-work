package com.example.ezjob.model.dto;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmailRequestDto {
    @Nonnull
    String msgBody;
    @Nonnull
    String subject;
}