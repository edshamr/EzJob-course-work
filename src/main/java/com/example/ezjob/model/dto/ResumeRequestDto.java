package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;
import java.util.List;

@Value
@Builder
public class ResumeRequestDto {
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
}
