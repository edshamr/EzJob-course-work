package com.example.ezjob.service.impl;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.RoleName;
import com.example.ezjob.persistense.repository.AuthenticationUserRepository;
import com.example.ezjob.service.AuthenticationUserService;
import jakarta.annotation.Nonnull;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationUserServiceImpl implements AuthenticationUserService {
  AuthenticationUserRepository authenticationUserRepository;

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
  public AuthenticationUser saveUser(AuthenticationUser user) {
    user.setRoles(Set.of(RoleName.USER));
    return authenticationUserRepository.save(user);
  }
}
