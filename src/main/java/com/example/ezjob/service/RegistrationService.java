package com.example.ezjob.service;

import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface RegistrationService {
  @Nullable
  AuthenticationResponseDto registerUser(@Nonnull RegistrationRequestDto registrationRequest);
}
