package com.example.ezjob.exception;

import java.io.Serial;

public class UserAlreadyExistException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 686921757946674249L;

  public UserAlreadyExistException(String message) {
    super(message);
  }
}
