package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import jakarta.annotation.Nullable;

import javax.validation.constraints.NotNull;

public interface UserRegistrationService {
  @Nullable
  AuthenticationUser createUser(@NotNull final String username,
                                @NotNull final String password,
                                @NotNull final String email);
}
