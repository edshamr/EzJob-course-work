package com.example.ezjob.common.mapper;

import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.RoleName;
import jakarta.annotation.Nonnull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AuthenticationUserMapper {

  @Mapping(source = "role", target = "role", qualifiedByName = "toRoleNameEnum")
  AuthenticationUser toAuthenticationUser(@Nonnull RegistrationRequestDto registrationRequest);

  AuthenticationResponseDto toAuthenticationResponseDto(@Nonnull AuthenticationUser user, @Nonnull String token);

  @Named("toRoleNameEnum")
  default RoleName toRoleNameEnum(@Nonnull String role) {
    try {
      return RoleName.valueOf(role);
    } catch (IllegalArgumentException e) {
      return RoleName.USER;
    }
  }
}
