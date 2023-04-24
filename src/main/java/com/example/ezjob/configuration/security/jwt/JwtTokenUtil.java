package com.example.ezjob.configuration.security.jwt;

import com.example.ezjob.persistense.entity.RoleName;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Set;
import org.springframework.security.core.Authentication;

/**
 * Interface to provide methods for creating, validating, etc. JWT token
 */
public interface JwtTokenUtil {
  /**
   * Retrieve a username from the JWT token.
   *
   * @param token a JWT token with needed information
   * @return user username from passed token
   */
  @Nonnull
  String getUsername(@Nonnull String token);

  /**
   * Retrieve expiration date from the JWT token.
   *
   * @param token a JWT token with needed information
   * @return expiration date contained in the token
   */
  @Nonnull
  Date getExpirationDate(@Nonnull String token);

  /**
   * Retrieve the authentication information from the JWT token.
   *
   * @param token the JWT token to extract the authentication information from
   * @return the authentication information from the token
   */
  @Nonnull
  Authentication getAuthentication(@Nonnull String token);

  /**
   * Creates a new JWT token based on give data.
   *
   * @param username a username to include in token
   * @param roles    set of roles to include in token
   * @return the created JWT token
   */
  @Nonnull
  String createToken(@Nonnull String username, @Nonnull Set<RoleName> roles);

  /**
   * Validates the JWT token.
   *
   * @param token a JWT token to check
   * @return {@code true} if token invalid {@code false} otherwise
   */
  @Nonnull
  Boolean isTokenValid(@Nonnull String token);

  /**
   * Parse the JWT token from the given HTTP servlet request.
   *
   * @param request the HTTP servlet request to extract the JWT token from
   * @return the resolved JWT token {@null} otherwise
   */
  @Nullable
  String parseJwt(@Nonnull HttpServletRequest request);
}
