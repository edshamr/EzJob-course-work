package com.example.ezjob.service.impl;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.repository.AuthUserRepository;
import com.example.ezjob.service.UserRegistrationService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserRegistrationServiceImpl
        implements UserRegistrationService {

  private final AuthUserRepository authUserRepository;

  @Override
  @Nullable
  public AuthenticationUser registerUser(AuthenticationUser user) {
    return authUserRepository.save(user);
  }
}