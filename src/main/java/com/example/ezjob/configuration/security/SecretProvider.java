package com.example.ezjob.configuration.security;

import jakarta.annotation.Nonnull;

public interface SecretProvider {
  /**
   * Returns an encoded secret
   * @return the encoded secret
   */
  @Nonnull
  String getEncodedSecret();
}
