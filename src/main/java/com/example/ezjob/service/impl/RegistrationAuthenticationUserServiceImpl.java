package com.example.ezjob.service.impl;

import com.example.ezjob.common.validation.AuthUserValidator;
import com.example.ezjob.exception.UserAlreadyExistException;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.RoleName;
import com.example.ezjob.persistense.repository.AuthUserRepository;
import com.example.ezjob.service.RegistrationAuthenticationUserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RegistrationAuthenticationUserServiceImpl
        implements RegistrationAuthenticationUserService {

  private final AuthUserRepository authUserRepository;
  private final AuthUserValidator userValidator;

  @Override
  @Nullable
  public AuthenticationUser createUser(@NotNull final AuthenticationUser user) {
    final var username = user.getUsername();
    final var email = user.getEmail();

    checkIfCredentialsAlreadyRegistered(username, email);
    addExtraInformation(user);
    return authUserRepository.save(user);
  }

  /**
   * Add additional information to the credentials that the user has passed.
   *
   * @param incompleteUser an object with the user credentials from the HTTP request.
   */
  private void addExtraInformation(final AuthenticationUser incompleteUser) {
    incompleteUser.setRoles(Set.of(RoleName.USER));
  }

  /**
   * Check for uniqueness of user credentials.
   *
   * @param username a username of user.
   * @param email an email of user.
   */
  private void checkIfCredentialsAlreadyRegistered(final String username, final String email) {
    if (userValidator.isEmailExistInDb(email) || userValidator.isUsernameExistInDb(username)) {
      throw new UserAlreadyExistException("User already exist");
    }
  }
}