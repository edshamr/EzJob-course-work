package com.example.ezjob.controller;

import com.example.ezjob.service.JwtTokenService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class LogoutController {
    private final JwtTokenService jwtTokenService;

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@Nonnull final HttpServletRequest request) {
        jwtTokenService.expireToken(request);
        log.info("Token successfully expired");
    }
}
