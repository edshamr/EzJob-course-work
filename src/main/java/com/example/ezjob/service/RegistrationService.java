package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.Resume;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface RegistrationService {
  @Nullable
  AuthenticationUser registerUser(@Nonnull AuthenticationUser user, @Nonnull Resume resume);
}
