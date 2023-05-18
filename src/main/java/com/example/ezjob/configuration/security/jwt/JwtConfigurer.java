package com.example.ezjob.configuration.security.jwt;

import com.example.ezjob.common.validation.AllowedPathValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtConfigurer
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private final JwtTokenUtil jwtTokenUtil;
  private final AllowedPathValidator pathValidator;

  @Override
  public void configure(@NonNull final HttpSecurity httpSecurity) {
    final var jwtTokenFilter = new JwtRequestFilter(jwtTokenUtil, pathValidator);
    httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
