package com.example.ezjob.controller;

import com.example.ezjob.common.TestConstants;
import com.example.ezjob.common.TestConstants.JwtTokenAttributes;
import com.example.ezjob.common.TestConstants.Path;
import com.example.ezjob.common.TestConstants.UserAttributes;
import com.example.ezjob.common.mapper.AuthenticationUserMapper;
import com.example.ezjob.common.validation.AuthUserValidator;
import com.example.ezjob.exception.NotFoundException;
import com.example.ezjob.exception.UserAlreadyExistException;
import com.example.ezjob.model.dto.AuthenticationRequestDto;
import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.service.AuthenticationUserService;
import com.example.ezjob.service.RegistrationService;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationControllerTest {
    @Value("${authentication.exception}")
    private String authenticationException;
    @Value("${not.found.exception}")
    private String notFoundException;
    @Value("${user.already.exist.exception}")
    private String userAlreadyExistsException;
    private static final String FULL_VALID_TOKEN = JwtTokenAttributes.TOKEN_PREFIX + JwtTokenAttributes.VALID_TOKEN;
    private static final AuthenticationRequestDto AUTHENTICATION_REQUEST_DTO = AuthenticationRequestDto.builder()
            .username(UserAttributes.USERNAME)
            .password(UserAttributes.PASSWORD)
            .build();
    private static final AuthenticationResponseDto AUTHENTICATION_RESPONSE_DTO = AuthenticationResponseDto.builder()
            .username(UserAttributes.USERNAME)
            .token(FULL_VALID_TOKEN)
            .build();
    private static final AuthenticationUser USER = AuthenticationUser.builder()
            .username(UserAttributes.USERNAME)
            .password(UserAttributes.PASSWORD)
            .roles(UserAttributes.ROLES)
            .build();

    private static final RegistrationRequestDto REGISTRATION_REQUEST = RegistrationRequestDto.builder()
            .username(UserAttributes.USERNAME)
            .password(UserAttributes.PASSWORD)
            .email(UserAttributes.EMAIL)
            .firstname(TestConstants.ResumeAttributes.FIRSTNAME)
            .lastname(TestConstants.ResumeAttributes.LASTNAME)
            .patronymic(TestConstants.ResumeAttributes.PATRONYMIC)
            .city(TestConstants.ResumeAttributes.CITY)
            .country(TestConstants.ResumeAttributes.COUNTRY)
            .phone(TestConstants.ResumeAttributes.PHONE)
            .position(TestConstants.ResumeAttributes.POSITION)
            .university(TestConstants.ResumeAttributes.UNIVERSITY)
            .skillsList(TestConstants.ResumeAttributes.SKILLS_LIST)
            .experience(TestConstants.ResumeAttributes.EXPERIENCE)
            .additionalInfo(TestConstants.ResumeAttributes.ADDITIONAL_INFO)
            .build();
    private final ObjectWriter OBJECT_WRITER = new ObjectMapper().writer().withDefaultPrettyPrinter();
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private AuthenticationUserService userService;
    @MockBean
    private TokenProviderService tokenProviderService;
    @MockBean
    private RegistrationService userRegistrationService;
    @MockBean
    private AuthenticationUserMapper userMapper;
    @MockBean
    private AuthUserValidator userValidator;
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnOkResponseWithValidBodyWhenInvokedLoginWithValidData() throws Exception {
        final var json = OBJECT_WRITER.writeValueAsString(AUTHENTICATION_REQUEST_DTO);

        when(authenticationManager.authenticate(any())).thenReturn(mock(Authentication.class));
        when(userService.getUserByUsername(UserAttributes.USERNAME)).thenReturn(USER);
        when(tokenProviderService.createToken(UserAttributes.USERNAME, UserAttributes.ROLES)).thenReturn(FULL_VALID_TOKEN);
        when(userMapper.toAuthenticationResponseDto(USER, FULL_VALID_TOKEN)).thenReturn(AUTHENTICATION_RESPONSE_DTO);

        mockMvc.perform(post(Path.LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(UserAttributes.USERNAME))
                .andExpect(jsonPath("$.token").value(FULL_VALID_TOKEN));

        verify(authenticationManager, only()).authenticate(any());
        verify(userService, only()).getUserByUsername(any());
        verify(tokenProviderService, only()).createToken(any(), any());
    }

    @Test
    void shouldThrowAuthenticationExceptionWhenInvokedLoginWithInvalidData() throws Exception {
        final var json = OBJECT_WRITER.writeValueAsString(AUTHENTICATION_REQUEST_DTO);
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
        final var json = OBJECT_WRITER.writeValueAsString(AUTHENTICATION_REQUEST_DTO);
        when(userService.getUserByUsername(UserAttributes.USERNAME)).thenReturn(null);

        mockMvc.perform(post(Path.LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(jsonPath("$.description").value(notFoundException));
    }

    @Test
    void shouldReturnOkResponseWithValidBodyWhenInvokedRegisterWithValidData() throws Exception {
        final var json = OBJECT_WRITER.writeValueAsString(REGISTRATION_REQUEST);
        when(userValidator.isUsernameExistInDb(UserAttributes.USERNAME)).thenReturn(false);
        when(userValidator.isEmailExistInDb(UserAttributes.EMAIL)).thenReturn(false);
        when(userRegistrationService.registerUser(REGISTRATION_REQUEST)).thenReturn(AUTHENTICATION_RESPONSE_DTO);

        mockMvc.perform(post(Path.REGISTRATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(UserAttributes.USERNAME))
                .andExpect(jsonPath("$.token").value(FULL_VALID_TOKEN));

        verify(userRegistrationService, only()).registerUser(any());
    }

    @Test
    void shouldReturnUserAlreadyExistsExceptionWhenInvokedRegisterWithInvalidUsername() throws Exception {
        final var json = OBJECT_WRITER.writeValueAsString(REGISTRATION_REQUEST);
        when(userValidator.isUsernameExistInDb(UserAttributes.USERNAME)).thenReturn(true);

        mockMvc.perform(post(Path.REGISTRATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserAlreadyExistException))
                .andExpect(jsonPath("$.description").value(userAlreadyExistsException));
    }

    @Test
    void shouldReturnUserAlreadyExistsExceptionWhenInvokedRegisterWithInvalidEmail() throws Exception {
        final var json = OBJECT_WRITER.writeValueAsString(REGISTRATION_REQUEST);
        when(userValidator.isUsernameExistInDb(UserAttributes.USERNAME)).thenReturn(false);
        when(userValidator.isEmailExistInDb(UserAttributes.EMAIL)).thenReturn(true);

        mockMvc.perform(post(Path.REGISTRATION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserAlreadyExistException))
                .andExpect(jsonPath("$.description").value(userAlreadyExistsException));
    }
}
