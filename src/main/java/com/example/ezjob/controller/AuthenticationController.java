package com.example.ezjob.controller;

import com.example.ezjob.common.mapper.AuthenticationUserMapper;
import com.example.ezjob.common.validation.AuthUserValidator;
import com.example.ezjob.exception.UserAlreadyExistException;
import com.example.ezjob.exception.UserNotFoundException;
import com.example.ezjob.model.dto.AuthenticationRequestDto;
import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.persistense.entity.RoleName;
import com.example.ezjob.service.AuthenticationUserService;
import com.example.ezjob.service.RegistrationService;
import com.example.ezjob.service.TokenProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
@Validated
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final RegistrationService userRegistrationService;
    private final AuthUserValidator userValidator;
    private final AuthenticationUserMapper userMapper;
    private final AuthenticationUserService userService;
    private final TokenProviderService tokenProviderService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDto login(
            @NotNull @Valid @RequestBody final AuthenticationRequestDto requestDto) {
        final var username = requestDto.getUsername();
        final var password = requestDto.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            final var user = userService.getUserByUsername(username);

            if (user == null) {
                throw new UserNotFoundException("User was not found");
            }

            final var token = tokenProviderService.createToken(user.getUsername(), user.getRole());

            AuthenticationResponseDto response = AuthenticationResponseDto.builder().build();

            if (user.getRole().equals(RoleName.USER)) {
                response = userMapper.toResumeAuthenticationResponseDto(user, token);
            }
            else if (user.getRole().equals(RoleName.COMPANY)) {
                response = userMapper.toCompanyAuthenticationResponseDto(user, token);
            }

            return response;

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(value = "/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponseDto register(
            @Valid @NotNull @RequestBody final RegistrationRequestDto registrationRequest) {
        final var username = registrationRequest.getUsername();
        final var email = registrationRequest.getEmail();

        if (userValidator.isEmailExistInDb(email) || userValidator.isUsernameExistInDb(username)) {
            throw new UserAlreadyExistException("User already exist.");
        }

        final var response = userRegistrationService.registerUser(registrationRequest);

        return response;
    }
}
