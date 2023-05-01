package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationUserMapper {
  AuthenticationUser toAuthenticationUser(RegistrationRequestDto registrationRequest);
  AuthenticationResponseDto toAuthenticationResponseDto(AuthenticationUser user, String token);
}
