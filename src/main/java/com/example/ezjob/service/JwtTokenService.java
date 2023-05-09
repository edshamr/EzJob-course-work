package com.example.ezjob.service;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtTokenService {
    void expireToken(HttpServletRequest request);
}
