package com.example.ezjob.configuration.security.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.ezjob.common.TestConstants.UserAttributes;
import com.example.ezjob.common.TestConstants.JwtTokenAttributes;
import com.example.ezjob.common.TestConstants.InvalidData;
import com.example.ezjob.exception.JwtAuthenticationException;
import com.example.ezjob.exception.JwtTokenIllegalArgumentException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class JwtUtilImplTest {
  @MockBean
  private UserDetailsService userDetailsService;
  @MockBean
  private HttpServletRequest httpServletRequest;
  @Autowired
  JwtTokenUtilImpl jwtTokenUtil;

  @Test
  void getUsernameShouldReturnSameUsernameAsWePassedInToken() {
    final var token = jwtTokenUtil.createToken(UserAttributes.USERNAME, UserAttributes.ROLES);

    final var usernameFromToken = jwtTokenUtil.getUsername(token);

    assertEquals(UserAttributes.USERNAME, usernameFromToken);
  }

  @Test
  void getAuthenticationShouldReturnAuthenticationObjectWithRightData() {
    final var token = jwtTokenUtil.createToken(UserAttributes.USERNAME, UserAttributes.ROLES);
    final var userDetails = mock(UserDetails.class);

    when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);

    final var authentication = jwtTokenUtil.getAuthentication(token);

    assertNotNull(authentication);
    assertEquals(userDetails, authentication.getPrincipal());
    assertEquals("", authentication.getCredentials());
    assertEquals(userDetails.getAuthorities(), authentication.getAuthorities());
  }

  @Test
  void createTokenShouldReturnValidTokenWhenPassValidData() {
    final var token = jwtTokenUtil.createToken(UserAttributes.USERNAME, UserAttributes.ROLES);

    final var isTokenValid = jwtTokenUtil.isTokenValid(token);

    assertTrue(isTokenValid);
  }

  @Test
  void parseJwtShouldParseBearerTokenToToken() {
    final var token = "Bearer token";
    final var expectedToken = "token";
    when(httpServletRequest.getHeader(JwtTokenAttributes.HEADER)).thenReturn(token);

    final var parsedToken = jwtTokenUtil.parseJwt(httpServletRequest);

    assertEquals(expectedToken, parsedToken);
  }

  @Test
  void parseJwtShouldReturnNullWhenInvalidTokenPassed() {
    final var invalidToken = InvalidData.InvalidJwtTokenAttributes.INVALID_TOKEN;
    when(httpServletRequest.getHeader(JwtTokenAttributes.HEADER)).thenReturn(invalidToken);
    assertNull(jwtTokenUtil.parseJwt(httpServletRequest));
  }

  @Test
  void isValidShouldReturnTrueWhenPassedValidToken() {
    final var tokenIsValid = jwtTokenUtil.isTokenValid(JwtTokenAttributes.VALID_TOKEN);
    assertTrue(tokenIsValid);
  }

  @Test
  void isValidShouldThrowJwtTokenIllegalArgumentExceptionWhenEmptyTokenPassed() {
    final var token = StringUtils.EMPTY;

    assertThrows(JwtTokenIllegalArgumentException.class, () -> jwtTokenUtil.isTokenValid(token));
  }

  @Test
  void isValidShouldThrowJwtTokenIllegalArgumentExceptionWhenInvalidTokenPassed() {
    final var token = InvalidData.InvalidJwtTokenAttributes.INVALID_TOKEN;

    assertThrows(JwtAuthenticationException.class, () -> jwtTokenUtil.isTokenValid(token));
  }
}
