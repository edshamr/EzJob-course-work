package com.example.ezjob.service;

import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;

public interface UserMapperService {
  AuthenticationUser registrationDtoToAuthenticationUser(RegistrationRequestDto registrationDto);
}
