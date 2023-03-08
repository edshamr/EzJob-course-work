package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.AuthenticationUser;

import javax.validation.constraints.NotNull;

public interface RegistrationAuthenticationUserService {
  AuthenticationUser createUser(@NotNull final AuthenticationUser user);
}
