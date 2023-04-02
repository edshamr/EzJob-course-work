package com.example.ezjob.controller;

import com.example.ezjob.common.validation.AuthUserValidator;
import com.example.ezjob.exception.UserAlreadyExistException;
import com.example.ezjob.model.dto.AuthenticationRequestDto;
import com.example.ezjob.model.dto.AuthenticationResponseDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.persistense.repository.AuthUserRepository;
import com.example.ezjob.service.UserMapperService;
import com.example.ezjob.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;
  private final UserRegistrationService userRegistrationService;
  private final AuthUserValidator userValidator;
  private final UserMapperService userProviderService;

  private final AuthUserRepository repository;
  @GetMapping()
  public Map<String, String>  defaultMethod() {
    return Map.of(
            "username", "testUsername",
            "password", "12345678",
            "email", "test@gmail.com");
  }

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
  @ResponseStatus(HttpStatus.OK)
  public AuthenticationResponseDto register(
          @Valid @NotNull @RequestBody final RegistrationRequestDto registrationRequest) {
    final var username = registrationRequest.getUsername();
    final var email = registrationRequest.getEmail();

    if (userValidator.isEmailExistInDb(email) || userValidator.isUsernameExistInDb(username)) {
      throw new UserAlreadyExistException("User already exist.");
    }

    final var authUser =
            userProviderService.registrationDtoToAuthenticationUser(registrationRequest);

    final var newUser =
            userRegistrationService.registerUser(authUser);


    return AuthenticationResponseDto.builder()
            .username(newUser.getUsername())
            .build();
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
