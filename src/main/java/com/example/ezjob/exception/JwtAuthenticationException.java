package com.example.ezjob.exception;

import io.jsonwebtoken.JwtException;
import java.io.Serial;

public class JwtAuthenticationException extends JwtException {

  @Serial
  private static final long serialVersionUID = -3667458873681771450L;

  public JwtAuthenticationException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
