package com.example.ezjob.service;

import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.Resume;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface RegistrationService {
  @Nullable
  AuthenticationUser registerUser(@Nonnull RegistrationRequestDto registrationRequest);
}
