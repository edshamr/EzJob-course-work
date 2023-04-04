package com.example.ezjob.service.impl;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.RoleName;
import com.example.ezjob.persistense.repository.AuthUserRepository;
import com.example.ezjob.service.AuthenticationUserService;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationUserServiceImpl implements AuthenticationUserService {
  AuthUserRepository authUserRepository;

  @Override
  public AuthenticationUser getUserByUsername(@Nonnull String username) {
    return authUserRepository.findByUsername(username);
  }

  @Override
  public AuthenticationUser getUserByEmail(@Nonnull String email) {
    return authUserRepository.findByEmail(email);
  }

  @Override
  public AuthenticationUser getUserById(@Nonnull Long id) {
    Optional<AuthenticationUser> user = authUserRepository.findById(id);
    return user.get();
  }

  @Override
  @Transactional
  public AuthenticationUser saveUser(AuthenticationUser user) {
    user.setRoles(Set.of(RoleName.USER));
    return authUserRepository.save(user);
  }
}
