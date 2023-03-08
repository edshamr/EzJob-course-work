package com.example.ezjob.exception;

import java.io.Serial;

/**
 * Exception class representing HTTP 404(Not found).
 */
public class NotFoundException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 7508454331267540093L;

  public NotFoundException(String msg) {
    super(msg);
  }
}