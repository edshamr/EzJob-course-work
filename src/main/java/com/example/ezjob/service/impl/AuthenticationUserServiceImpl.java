package com.example.ezjob.service.impl;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.repository.jpa.AuthenticationUserRepository;
import com.example.ezjob.service.AuthenticationUserService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationUserServiceImpl implements AuthenticationUserService {
  private final AuthenticationUserRepository authenticationUserRepository;

  @Override
  public AuthenticationUser getUserByUsername(@Nonnull final String username) {
    return authenticationUserRepository.findByUsername(username);
  }

  @Override
  public AuthenticationUser getUserByEmail(@Nonnull final String email) {
    return authenticationUserRepository.findByEmail(email);
  }

  @Override
  public AuthenticationUser getUserById(@Nonnull final Long id) {
    Optional<AuthenticationUser> user = authenticationUserRepository.findById(id);
    return user.get();
  }

  @Override
  @Transactional
  public AuthenticationUser saveUser(@Nonnull final AuthenticationUser user) {
    return authenticationUserRepository.save(user);
  }
}
