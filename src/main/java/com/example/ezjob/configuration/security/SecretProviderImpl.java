package com.example.ezjob.configuration.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SecretProviderImpl implements SecretProvider {
  private final String encodedSecret;

  public SecretProviderImpl(@Nonnull @Value("${spring.security.secret}") String secret) {
    this.encodedSecret = Base64.getEncoder()
            .encodeToString(secret.getBytes(StandardCharsets.UTF_8));
  }
}
