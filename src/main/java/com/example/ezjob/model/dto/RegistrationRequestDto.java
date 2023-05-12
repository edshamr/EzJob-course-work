package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@Value
public class RegistrationRequestDto {
  @NotBlank
  String username;

  @NotBlank
  String password;

  @NotBlank
  String email;

  String firstname;

  String lastname;

  String patronymic;

  String city;

  String country;

  String phone;

  String position;

  String university;

  List<String> skillsList;

  int experience;

  String additionalInfo;

  String role;
}
