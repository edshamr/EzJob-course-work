package com.example.ezjob.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserProfileResponseDto {
    String username;
    String email;
    String phone;
    String country;
    String position;
}
