package com.example.ezjob;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {EzJobApplication.class})
@ActiveProfiles("test")
class RegistrationTests{
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void shouldReturnBadRequestStatusWhenProvidedInvalidUserCredentials() {
    final var userCredentials = Map.of(
            "username", "testUser",
            "password", "12345678",
            "email", "test@gmail.com");

    final var response =
            restTemplate.postForEntity("/auth/user", userCredentials, Map.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void shouldReturnCreatedStatusWhenProvidedValidUniqueUserCredentials() {
    final var userCredentials = Map.of(
            "username", "registrationUser",
            "password", "12345678",
            "email", "registrationUser@gmail.com");

    final var response =
            restTemplate.postForEntity("/auth/user", userCredentials, Map.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }
}

