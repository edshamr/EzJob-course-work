package com.example.ezjob;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {EzJobApplication.class})
public class RegistrationTests{
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void shouldReturnBadRequestStatusWhenProvidedInvalidUserCredentials() {
    final var userCredentials = Map.of(
            "username", "testUsername",
            "password", "12345678",
            "email", "test@gmail.com");

    final var response =
            restTemplate.postForEntity("/auth/registration", userCredentials, Map.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}

