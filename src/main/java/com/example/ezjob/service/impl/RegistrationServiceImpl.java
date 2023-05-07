package com.example.ezjob.service.impl;

import com.example.ezjob.common.mapper.AuthenticationUserMapper;
import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.service.AuthenticationUserService;
import com.example.ezjob.service.RegistrationService;
import com.example.ezjob.service.ResumeService;
import com.example.ezjob.service.TokenProviderService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl
        implements RegistrationService {
  private final AuthenticationUserService userService;
  private final ResumeService resumeService;
  private final AuthenticationUserMapper userMapper;
  private final ResumeMapper resumeMapper;
  private final TokenProviderService tokenProviderService;
  @Override
  @Transactional
  @Nullable
  public AuthenticationResponseDto registerUser(@Nonnull RegistrationRequestDto registrationRequest) {
    final var authUser = userMapper.toAuthenticationUser(registrationRequest);
    final var resumeRequest = resumeMapper.toResumeRequestDto(registrationRequest);

    resumeService.saveResume(resumeRequest);
    final var savedUser = userService.saveUser(authUser.getUsername(),
            authUser.getPassword(),
            authUser.getEmail());

    final var token = tokenProviderService.createToken(savedUser.getUsername(), savedUser.getRoles());

    return userMapper.toAuthenticationResponseDto(savedUser, token);
  }
}