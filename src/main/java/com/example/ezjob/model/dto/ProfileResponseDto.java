package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProfileResponseDto {
    String username;
    String email;
    String phone;
    String country;
    String position;
}
