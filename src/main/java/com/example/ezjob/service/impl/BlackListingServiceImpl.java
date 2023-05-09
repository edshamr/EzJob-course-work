package com.example.ezjob.service.impl;

import com.example.ezjob.common.ApplicationConstants;
import com.example.ezjob.common.ApplicationConstants.Web.Security;
import com.example.ezjob.service.BlackListingService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListingServiceImpl implements BlackListingService {
    private final CacheManager cacheManager;
    @Override
    @CachePut(Security.BLACKLIST_CACHE_NAME)
    public String blackListJwt(String jwt) {
        return jwt;
    }

    @Override
    @Cacheable(value = Security.BLACKLIST_CACHE_NAME, unless = "#result == null")
    public String getBlackListedJwt(String jwt) {
        return null;
    }
}
