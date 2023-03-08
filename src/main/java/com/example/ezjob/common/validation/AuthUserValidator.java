package com.example.ezjob.common.validation;

import com.example.ezjob.persistense.repository.AuthUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Validation for user-related information.
 */
@Component
@RequiredArgsConstructor
public class AuthUserValidator {
  private final AuthUserRepository authUserRepository;

  /**
   * Check if the given email exists in the database.
   *
   * @param email the email to check
   * @return true if the email exists in the database, false otherwise
   */
  public boolean isEmailExistInDb(@NonNull final String email) {
    return authUserRepository.findByEmail(email) != null;
  }

  /**
   * Check if the given username exists in the database.
   *
   * @param username the username to check
   * @return true if the username exists in the database, false otherwise
   */
  public boolean isUsernameExistInDb(@NonNull final String username) {
    return authUserRepository.findByUsername(username) != null;
  }
}