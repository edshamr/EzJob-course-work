package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResumeRequestDto {
  String email;
  String firstname;
  String lastname;
  String patronymic;
  String city;
  String phone;
  String position;
  String university;
  String skills;
  int experience;
  String additionalInfo;
}
