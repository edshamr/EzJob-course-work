package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthenticationResponseDto {
  String username;
  String token;
}
