package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import jakarta.annotation.Nonnull;

public interface AuthenticationUserService {
    AuthenticationUser getUserByUsername(@Nonnull String username);
    AuthenticationUser getUserByEmail(@Nonnull String email);
    AuthenticationUser getUserById(@Nonnull Long id);
    AuthenticationUser saveUser(@Nonnull final String username,
                                @Nonnull final String password,
                                @Nonnull final String email);
}
