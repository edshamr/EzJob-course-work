package com.example.ezjob.exception;

import io.jsonwebtoken.JwtException;
import java.io.Serial;

public class JwtTokenIllegalArgumentException extends JwtException {
  @Serial
  private static final long serialVersionUID = 3849586475071596982L;

  public JwtTokenIllegalArgumentException(String message, Throwable cause) {
    super(message, cause);
  }
}
