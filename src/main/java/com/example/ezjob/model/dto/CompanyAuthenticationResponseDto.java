package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class CompanyAuthenticationResponseDto extends AuthenticationResponseDto {
  Long companyId;
}
