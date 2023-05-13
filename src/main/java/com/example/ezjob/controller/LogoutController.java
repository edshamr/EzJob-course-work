package com.example.ezjob.controller;

import com.example.ezjob.service.JwtTokenService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
@RequiredArgsConstructor
@Slf4j
public class LogoutController {
    private final JwtTokenService jwtTokenService;

    @PostMapping("/expire")
    public ResponseEntity<Void> logout(@Nonnull final HttpServletRequest request) {
        jwtTokenService.expireToken(request);
        log.info("Token successfully expired");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
