package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Builder
@Value
public class RegistrationRequestDto {
  @NotBlank
  String username;

  @NotBlank
  String password;

  @NotBlank
  String email;
}
