package com.example.ezjob.configuration.security;

import jakarta.annotation.Nonnull;

public interface SecretProvider {
  @Nonnull
  String getEncodedSecret();
}
