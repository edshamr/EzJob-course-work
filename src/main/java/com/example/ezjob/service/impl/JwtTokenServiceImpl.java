package com.example.ezjob.service.impl;

import com.example.ezjob.configuration.security.jwt.JwtTokenUtil;
import com.example.ezjob.service.BlackListingService;
import com.example.ezjob.service.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtTokenUtil jwtTokenUtil;
    private final BlackListingService blackListingService;
    @Override
    public void expireToken(HttpServletRequest request) {
        final var token = jwtTokenUtil.parseJwt(request);
        blackListingService.blackListJwt(token);
    }
}
