package com.example.ezjob.service.impl;

import com.example.ezjob.common.mapper.AuthenticationUserMapper;
import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.service.AuthenticationUserService;
import com.example.ezjob.service.RegistrationService;
import com.example.ezjob.service.ResumeService;
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
  @Override
  @Transactional
  @Nullable
  public AuthenticationUser registerUser(@Nonnull RegistrationRequestDto registrationRequest) {
    final var authUser = userMapper.toAuthenticationUser(registrationRequest);
    final var resume = resumeMapper.toResumeRequestDto(registrationRequest);
    resumeService.saveResume(resume);
    return userService.saveUser(authUser.getUsername(), authUser.getPassword(), authUser.getEmail());
  }
}