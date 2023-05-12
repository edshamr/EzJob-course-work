package com.example.ezjob.service.impl;

import com.example.ezjob.common.ApplicationConstants;
import com.example.ezjob.configuration.security.jwt.JwtTokenUtil;
import com.example.ezjob.persistense.entity.RoleName;
import com.example.ezjob.service.TokenProviderService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenProviderServiceImpl implements TokenProviderService {
  private final JwtTokenUtil jwtTokenUtil;

  /**
   * Create a new jwt token and concatenate it with the token prefix.
   *
   * @param username a user username.
   * @param role user role.
   * @return a new jwt token with the token prefix.
   */
  @Override
  @Nullable
  public String createToken(@Nonnull final String username,
                            @Nonnull final RoleName role) {
    return ApplicationConstants.Web.Security.TOKEN_PREFIX
            + jwtTokenUtil.createToken(username, role);
  }
}