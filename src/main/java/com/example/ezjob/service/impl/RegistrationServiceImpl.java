package com.example.ezjob.service.impl;

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
  @Override
  @Transactional
  @Nullable
  public AuthenticationUser registerUser(@Nonnull final AuthenticationUser user,
                                         @Nonnull final Resume resume) {
    resumeService.saveResume(resume);
    return userService.saveUser(user);
  }
}