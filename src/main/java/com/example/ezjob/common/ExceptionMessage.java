package com.example.ezjob.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:exception_message.properties")
@Getter
public class ExceptionMessage {
  @Value("${not.found.exception}")
  private String notFoundException;

  @Value("${authentication.exception}")
  private String authenticationException;

  @Value("${user.already.exist.exception}")
  private String userAlreadyExistException;
}
