package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.RoleName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AuthenticationUserMapper {
  @Mapping(source = "role", target = "role", qualifiedByName = "toRoleNameEnum")
  AuthenticationUser toAuthenticationUser(RegistrationRequestDto registrationRequest);
  AuthenticationResponseDto toAuthenticationResponseDto(AuthenticationUser user, String token);

  @Named("toRoleNameEnum")
  default RoleName toRoleNameEnum(String role) {
    try {
      return RoleName.valueOf(role);
    } catch (IllegalArgumentException e) {
      return RoleName.USER;
    }
  }
}
