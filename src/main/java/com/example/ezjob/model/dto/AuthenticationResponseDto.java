package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class AuthenticationResponseDto {
  @NotBlank
  String username;

  @NotBlank
  String token;
}
