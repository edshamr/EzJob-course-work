package com.example.ezjob.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.ezjob.common.TestConstants.JwtTokenAttributes;
import com.example.ezjob.common.TestConstants.Path;
import com.example.ezjob.common.TestConstants.UserAttributes;
import com.example.ezjob.exception.NotFoundException;
import com.example.ezjob.model.dto.AuthenticationRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.service.AuthenticationUserService;
import com.example.ezjob.service.TokenProviderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationControllerTest {
  @Value("${authentication.exception}")
  private String authenticationException;
  @Value("${not.found.exception}")
  private String notFoundException;
  private final AuthenticationRequestDto REQUEST_DTO = AuthenticationRequestDto.builder()
          .username(UserAttributes.USERNAME)
          .password(UserAttributes.PASSWORD)
          .build();
  private final AuthenticationUser USER = AuthenticationUser.builder()
          .username(UserAttributes.USERNAME)
          .password(UserAttributes.PASSWORD)
          .roles(UserAttributes.ROLES)
          .build();

  private final ObjectWriter OBJECT_WRITER = new ObjectMapper().writer().withDefaultPrettyPrinter();
  @MockBean
  private AuthenticationManager authenticationManager;
  @MockBean
  private AuthenticationUserService userService;
  @MockBean
  private TokenProviderService tokenProviderService;
  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldReturnOkResponseWithValidBodyWhenInvokedLoginWithValidData() throws Exception {
    final var expectedToken = JwtTokenAttributes.TOKEN_PREFIX + JwtTokenAttributes.VALID_TOKEN;
    final var json = OBJECT_WRITER.writeValueAsString(REQUEST_DTO);

    when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));
    when(userService.getUserByUsername(UserAttributes.USERNAME)).thenReturn(USER);
    when(tokenProviderService.createToken(UserAttributes.USERNAME, UserAttributes.ROLES)).thenReturn(expectedToken);

    mockMvc.perform(post(Path.LOGIN_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value(UserAttributes.USERNAME))
            .andExpect(jsonPath("$.token").value(expectedToken));

    verify(authenticationManager, only()).authenticate(any());
    verify(userService, only()).getUserByUsername(any());
    verify(tokenProviderService, only()).createToken(any(), any());
  }

  @Test
  void shouldThrowAuthenticationExceptionWhenInvokedLoginWithInvalidData() throws Exception {
    final var json = OBJECT_WRITER.writeValueAsString(REQUEST_DTO);
    when(authenticationManager.authenticate(any())).thenThrow(BadCredentialsException.class);

    mockMvc.perform(post(Path.LOGIN_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isUnauthorized())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof AuthenticationException))
            .andExpect(jsonPath("$.description").value(authenticationException));
  }

  @Test
  void shouldThrowUserNotFoundExceptionWhenInvokedLoginWithInvalidCredentials() throws Exception {
    final var json = OBJECT_WRITER.writeValueAsString(REQUEST_DTO);
    when(userService.getUserByUsername(UserAttributes.USERNAME)).thenReturn(null);

    mockMvc.perform(post(Path.LOGIN_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isNotFound())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
            .andExpect(jsonPath("$.description").value(notFoundException));
  }
}
