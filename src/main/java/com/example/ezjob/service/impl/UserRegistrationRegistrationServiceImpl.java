package com.example.ezjob.service.impl;

import com.example.ezjob.common.validation.AuthUserValidator;
import com.example.ezjob.exception.UserAlreadyExistException;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.RoleName;
import com.example.ezjob.persistense.repository.AuthUserRepository;
import com.example.ezjob.service.UserRegistrationService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserRegistrationRegistrationServiceImpl
        implements UserRegistrationService {

  private final AuthUserRepository authUserRepository;
  private final AuthUserValidator userValidator;

  @Override
  @Nullable
  public AuthenticationUser createUser(@NotNull final String username,
                                       @NotNull final String password,
                                       @NotNull final String email) {
    checkIfCredentialsAlreadyRegistered(username, email);
    final var user = generateUser(username, password, email);
    return authUserRepository.save(user);
  }

  /**
   * Generate a new user based on passed data.
   *
   * @param username a username of user.
   * @param password password of user.
   * @param email an email address of user.
   * @return AuthenticationUser based on passed data from the request.
   */
  @NotNull
  private AuthenticationUser generateUser(@NotNull final String username,
                                          @NotNull final String password,
                                          @NotNull final String email) {
    return AuthenticationUser.builder()
            .username(username)
            .password(password)
            .email(email)
            .roles(Set.of(RoleName.USER))
            .build();
  }

  /**
   * Check for uniqueness of user credentials.
   *
   * @param username a username of user.
   * @param email    an email of user.
   */
  private void checkIfCredentialsAlreadyRegistered(final String username, final String email) {
    if (userValidator.isEmailExistInDb(email) || userValidator.isUsernameExistInDb(username)) {
      throw new UserAlreadyExistException("User already exist");
    }
  }
}