package com.example.ezjob.controller;

import com.example.ezjob.common.mapper.AuthenticationUserMapper;
import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.common.validation.AuthUserValidator;
import com.example.ezjob.exception.UserAlreadyExistException;
import com.example.ezjob.exception.UserNotFoundException;
import com.example.ezjob.model.dto.AuthenticationRequestDto;
import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.persistense.repository.AuthUserRepository;
import com.example.ezjob.service.AuthenticationUserService;
import com.example.ezjob.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;
  private final RegistrationService userRegistrationService;
  private final AuthUserValidator userValidator;
  private final AuthenticationUserMapper userMapper;
  private final AuthUserRepository repository;
  private final AuthenticationUserService userService;
  private final ResumeMapper resumeMapper;
  @PostMapping("/login")
  public ResponseEntity<String> login(
          @Valid @NotNull @RequestBody final AuthenticationRequestDto requestDto) {
    final var username = requestDto.getUsername();
    final var password = requestDto.getPassword();

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
    return ResponseEntity.ok().build();
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

    final var resume =
            resumeMapper.toResume(registrationRequest);

    final var newUser =
            userRegistrationService.registerUser(authUser, resume);


    return AuthenticationResponseDto.builder()
            .username(newUser.getUsername())
            .build();
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public AuthenticationResponseDto responseDto(@PathVariable @NotNull @Min(1) final Long id) {
    final var user = userService.getUserById(id);
    if (user == null) {
      throw new UserNotFoundException("There isn`t such user");
    }
    return userMapper.toAuthenticationResponseDto(user);
  }

  @GetMapping(value = "/test")
  public AuthenticationResponseDto test() {
    final var user = repository.findByUsername("testUsername");
    return AuthenticationResponseDto.builder()
            .username(user.getUsername())
            .email(user.getEmail())
            .build();
  }
}
