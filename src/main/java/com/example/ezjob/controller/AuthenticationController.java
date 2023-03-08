package com.example.ezjob.controller;

import com.example.ezjob.model.dto.AuthenticationRequestDto;
import com.example.ezjob.model.dto.RegistrationRequestDto;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.repository.AuthUserRepository;
import com.example.ezjob.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;
  private final UserRegistrationService registrationAuthenticationUserService;

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

  @PostMapping(value = "/registration")
  public ResponseEntity<String> register(
          @Valid @RequestBody final RegistrationRequestDto requestDto) {
    final var username = requestDto.getUsername();
    final var password = requestDto.getPassword();
    final var email = requestDto.getEmail();

    registrationAuthenticationUserService.createUser(username, password, email);

    return ResponseEntity.ok().build();
  }
}
