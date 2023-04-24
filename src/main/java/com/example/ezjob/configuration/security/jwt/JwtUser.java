package com.example.ezjob.configuration.security.jwt;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class JwtUser implements UserDetails {
  final Long id;
  final String username;
  final String email;
  @JsonIgnore
  final String password;
  private final Collection<? extends GrantedAuthority> authorities;

  @Nonnull
  public static JwtUser build(@Nonnull AuthenticationUser user) {
    final var authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.toString()))
            .toList();

    return new JwtUser(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
