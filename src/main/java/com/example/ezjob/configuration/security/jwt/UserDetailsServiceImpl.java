package com.example.ezjob.configuration.security.jwt;

import com.example.ezjob.service.AuthenticationUserService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final AuthenticationUserService userService;

  @Override
  public UserDetails loadUserByUsername(@Nonnull final String username) throws UsernameNotFoundException {
    final var user = userService.getUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User Not Found with username:" + username);
    }

    return JwtUser.build(user);
  }
}
