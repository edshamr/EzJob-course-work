package com.example.ezjob.configuration.security.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.ezjob.common.TestConstants.UserAttributes;
import com.example.ezjob.persistense.entity.AuthenticationUser;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class JwtUserTest {

  @Test
  void buildShouldReturnValidJwtUserBasedOnPassedData() {
    final var user = AuthenticationUser.builder()
            .id(UserAttributes.ID)
            .username(UserAttributes.USERNAME)
            .password(UserAttributes.PASSWORD)
            .roles(UserAttributes.ROLES)
            .build();

    final var actualUser = JwtUser.build(user);
    assertEquals(UserAttributes.USERNAME, actualUser.getUsername());
    assertEquals(UserAttributes.PASSWORD, actualUser.getPassword());
    assertTrue(actualUser.isAccountNonExpired());
    assertTrue(actualUser.isAccountNonLocked());
    assertTrue(actualUser.isCredentialsNonExpired());
    assertTrue(actualUser.isEnabled());
  }
}
