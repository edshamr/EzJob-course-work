package com.example.ezjob.service.impl;

import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.RoleName;
import com.example.ezjob.service.UserMapperService;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserMapperServiceImpl implements UserMapperService {
  @Override
  public AuthenticationUser registrationDtoToAuthenticationUser(RegistrationRequestDto registrationDto) {
    return AuthenticationUser.builder()
            .username(registrationDto.getUsername())
            .password(registrationDto.getPassword())
            .email(registrationDto.getEmail())
            .roles(Set.of(RoleName.USER))
            .build();
  }
}
