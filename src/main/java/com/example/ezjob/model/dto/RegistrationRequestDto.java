package com.example.ezjob.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ToString
@EqualsAndHashCode
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequestDto {
  @NotBlank
  @NotEmpty
  String username;

  @NotBlank
  @NotEmpty
  String password;

  @NotBlank
  @NotEmpty
  String email;
}
