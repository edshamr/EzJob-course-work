package com.example.ezjob.controller;

import com.example.ezjob.common.mapper.AuthenticationUserMapper;
import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.common.validation.AuthUserValidator;
import com.example.ezjob.exception.UserAlreadyExistException;
import com.example.ezjob.exception.UserNotFoundException;
import com.example.ezjob.model.dto.AuthenticationRequestDto;
import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.service.AuthenticationUserService;
import com.example.ezjob.service.RegistrationService;
import com.example.ezjob.service.TokenProviderService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
//TODO Fix registration
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;
  private final RegistrationService userRegistrationService;
  private final AuthUserValidator userValidator;
  private final AuthenticationUserMapper userMapper;
  private final AuthenticationUserService userService;
  private final ResumeMapper resumeMapper;
  private final TokenProviderService tokenProviderService;
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public AuthenticationResponseDto login(
          @Valid @NotNull @RequestBody final AuthenticationRequestDto requestDto) {
    final var username = requestDto.getUsername();
    final var password = requestDto.getPassword();

    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(username, password));

      final var user = userService.getUserByUsername(username);

      if (user == null) {
        throw new UserNotFoundException("User was not found");
      }

      final var token = tokenProviderService.createToken(user.getUsername(), user.getRoles());

      return userMapper.toAuthenticationResponseDto(user, token);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }

  @PostMapping(value = "/user")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthenticationResponseDto register(
          @Valid @NotNull @RequestBody final RegistrationRequestDto registrationRequest) {
    final var username = registrationRequest.getUsername();
    final var email = registrationRequest.getEmail();

    if (userValidator.isEmailExistInDb(email) || userValidator.isUsernameExistInDb(username)) {
      throw new UserAlreadyExistException("User already exist.");
    }

    final var authUser =
            userMapper.toAuthenticationUser(registrationRequest);

//    final var resume =
//            resumeMapper.toResume(registrationRequest);
    final var resume =
            ResumeRequestDto.builder().build();

    final var newUser =
            userRegistrationService.registerUser(registrationRequest);

    final var token = tokenProviderService.createToken(newUser.getUsername(), newUser.getRoles());

    return AuthenticationResponseDto.builder()
            .username(newUser.getUsername())
            .token(token)
            .build();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public AuthenticationResponseDto responseDto(@PathVariable @NotNull @Min(1) final Long id) {
    final var user = userService.getUserById(id);
    if (user == null) {
      throw new UserNotFoundException("There isn`t such user");
    }

    final var token = tokenProviderService.createToken(user.getUsername(), user.getRoles());

    return userMapper.toAuthenticationResponseDto(user, token);
  }
}
