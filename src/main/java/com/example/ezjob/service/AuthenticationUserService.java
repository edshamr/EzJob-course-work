package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.AuthenticationUser;
import com.example.ezjob.persistense.entity.RoleName;
import jakarta.annotation.Nonnull;

import java.util.Set;

public interface AuthenticationUserService {
    AuthenticationUser getUserByUsername(@Nonnull String username);
    AuthenticationUser getUserByEmail(@Nonnull String email);
    AuthenticationUser getUserById(@Nonnull Long id);
    AuthenticationUser saveUser(@Nonnull final String username,
                                @Nonnull final String password,
                                @Nonnull final String email,
                                @Nonnull final RoleName role);
}
