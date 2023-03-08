package com.example.ezjob.service.impl;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.repository.AuthUserRepository;
import com.example.ezjob.service.AuthenticationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationServiceImpl implements AuthenticationService {
  @Autowired
  AuthUserRepository authUserRepository;



  @Override
  public AuthenticationUser getUserByUsername(@NonNull String username) {
    return authUserRepository.findByUsername(username);
  }

  @Override
  public AuthenticationUser getUserByEmail(@NonNull String email) {
    return authUserRepository.findByEmail(email);
  }
}
