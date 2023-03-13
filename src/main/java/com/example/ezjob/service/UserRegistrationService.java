package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import jakarta.annotation.Nullable;

public interface UserRegistrationService {
  @Nullable
  AuthenticationUser registerUser(AuthenticationUser user);
}
