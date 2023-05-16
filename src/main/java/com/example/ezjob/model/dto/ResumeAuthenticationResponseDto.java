package com.example.ezjob.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class ResumeAuthenticationResponseDto extends AuthenticationResponseDto {
  Long resumeId;
}
