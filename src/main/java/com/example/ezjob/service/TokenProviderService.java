package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.RoleName;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Service to provide full JwtToken.
 */
public interface TokenProviderService {
    /**
     * Create a new jwt token.
     *
     * @param username a user username
     * @param role     user role
     * @return a token based on passed data
     */
    @Nullable
    String createToken(@Nonnull String username,
                       @Nonnull String email,
                       @Nonnull RoleName role);
}
