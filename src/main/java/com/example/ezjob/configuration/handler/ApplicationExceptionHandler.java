package com.example.ezjob.configuration.handler;

import com.example.ezjob.exception.UserAlreadyExistException;
import com.example.ezjob.persistense.entity.ErrorMessage;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApplicationExceptionHandler {

  @ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<ErrorMessage> handleAuthenticationException(
          @Nonnull final HttpServletRequest request,
          @Nonnull final Exception exception) {
    log.error("Exception was thrown cause user already exists:", exception);
    final var message = ErrorMessage.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .date(new Date())
            .description(exception.getMessage())
            .url(request.getRequestURL().toString())
            .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  @ExceptionHandler(JwtException.class)
  public ResponseEntity<ErrorMessage> handleJwtException(
          @Nonnull final HttpServletRequest request,
          @Nonnull final Exception exception) {
    log.error("Exception was thrown due authentication:", exception);
    final var message = ErrorMessage.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .date(new Date())
            .description(exception.getMessage())
            .url(request.getRequestURL().toString())
            .build();
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
  }
}
