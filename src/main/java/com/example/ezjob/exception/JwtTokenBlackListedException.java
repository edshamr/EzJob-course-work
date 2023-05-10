package com.example.ezjob.exception;

import io.jsonwebtoken.JwtException;

import java.io.Serial;

public class JwtTokenBlackListedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3455268360349415944L;

    public JwtTokenBlackListedException(String message) {
        super(message);
    }
}
