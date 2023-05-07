package com.example.ezjob.service;

import com.example.ezjob.common.TestConstants.JwtTokenAttributes;
import com.example.ezjob.common.TestConstants.ResumeAttributes;
import com.example.ezjob.common.TestConstants.UserAttributes;
import com.example.ezjob.common.mapper.AuthenticationUserMapper;
import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.service.impl.RegistrationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceImplTest {
    private static final RegistrationRequestDto REGISTRATION_REQUEST = RegistrationRequestDto.builder()
            .username(UserAttributes.USERNAME)
            .password(UserAttributes.PASSWORD)
            .email(UserAttributes.EMAIL)
            .firstname(ResumeAttributes.FIRSTNAME)
            .lastname(ResumeAttributes.LASTNAME)
            .patronymic(ResumeAttributes.PATRONYMIC)
            .city(ResumeAttributes.CITY)
            .country(ResumeAttributes.COUNTRY)
            .phone(ResumeAttributes.PHONE)
            .position(ResumeAttributes.POSITION)
            .university(ResumeAttributes.UNIVERSITY)
            .skillsList(ResumeAttributes.SKILLS_LIST)
            .experience(ResumeAttributes.EXPERIENCE)
            .additionalInfo(ResumeAttributes.ADDITIONAL_INFO)
            .build();

    private static final AuthenticationUser USER = AuthenticationUser.builder()
            .username(UserAttributes.USERNAME)
            .password(UserAttributes.PASSWORD)
            .email(UserAttributes.EMAIL)
            .roles(UserAttributes.ROLES)
            .build();

    private static final ResumeRequestDto RESUME_REQUEST = ResumeRequestDto.builder()
            .email(ResumeAttributes.EMAIL)
            .firstname(ResumeAttributes.FIRSTNAME)
            .lastname(ResumeAttributes.LASTNAME)
            .patronymic(ResumeAttributes.PATRONYMIC)
            .city(ResumeAttributes.CITY)
            .country(ResumeAttributes.COUNTRY)
            .phone(ResumeAttributes.PHONE)
            .position(ResumeAttributes.POSITION)
            .university(ResumeAttributes.UNIVERSITY)
            .skillsList(ResumeAttributes.SKILLS_LIST)
            .experience(ResumeAttributes.EXPERIENCE)
            .additionalInfo(ResumeAttributes.ADDITIONAL_INFO)
            .build();

    private static final Resume RESUME = Resume.builder()
            .email(ResumeAttributes.EMAIL)
            .firstname(ResumeAttributes.FIRSTNAME)
            .lastname(ResumeAttributes.LASTNAME)
            .patronymic(ResumeAttributes.PATRONYMIC)
            .city(ResumeAttributes.CITY)
            .country(ResumeAttributes.COUNTRY)
            .phone(ResumeAttributes.PHONE)
            .position(ResumeAttributes.POSITION)
            .university(ResumeAttributes.UNIVERSITY)
            .skillsList(ResumeAttributes.SKILLS_LIST)
            .experience(ResumeAttributes.EXPERIENCE)
            .additionalInfo(ResumeAttributes.ADDITIONAL_INFO)
            .build();

    private static final String FULL_VALID_TOKEN = JwtTokenAttributes.TOKEN_PREFIX + JwtTokenAttributes.VALID_TOKEN;

    private static final AuthenticationResponseDto AUTHENTICATION_RESPONSE = AuthenticationResponseDto.builder()
            .username(UserAttributes.USERNAME)
            .token(FULL_VALID_TOKEN)
            .build();

    @Mock
    private AuthenticationUserService userService;
    @Mock
    private ResumeService resumeService;
    @Mock
    private AuthenticationUserMapper userMapper;
    @Mock
    private ResumeMapper resumeMapper;
    @Mock
    private TokenProviderService tokenProviderService;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Test
    void shouldReturnAuthenticationResponseDtoWhenInvokedRegisterUserWithValidRegistrationRequestDto() {
        when(userMapper.toAuthenticationUser(REGISTRATION_REQUEST)).thenReturn(USER);
        when(resumeMapper.toResumeRequestDto(REGISTRATION_REQUEST)).thenReturn(RESUME_REQUEST);
        when(resumeService.saveResume(RESUME_REQUEST)).thenReturn(RESUME);
        when(userService.saveUser(USER.getUsername(), USER.getPassword(), USER.getEmail())).thenReturn(USER);
        when(tokenProviderService.createToken(USER.getUsername(), USER.getRoles())).thenReturn(FULL_VALID_TOKEN);
        when(userMapper.toAuthenticationResponseDto(USER, FULL_VALID_TOKEN)).thenReturn(AUTHENTICATION_RESPONSE);

        final var actualResponse = registrationService.registerUser(REGISTRATION_REQUEST);

        assertEquals(AUTHENTICATION_RESPONSE, actualResponse);
    }
}
