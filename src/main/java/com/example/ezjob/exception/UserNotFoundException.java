package com.example.ezjob.exception;

import java.io.Serial;

public class UserNotFoundException extends NotFoundException {

  @Serial
  private static final long serialVersionUID = 8327961551956036548L;

  public UserNotFoundException(String msg) {
    super(msg);
  }
}

