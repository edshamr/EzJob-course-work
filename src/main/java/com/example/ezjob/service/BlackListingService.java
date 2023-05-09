package com.example.ezjob.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface BlackListingService {
    @Nonnull
    String blackListJwt(@Nonnull String jwt);
    @Nullable
    String getBlackListedJwt(@Nonnull String jwt);
}
