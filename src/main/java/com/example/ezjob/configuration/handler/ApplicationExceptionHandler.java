package com.example.ezjob.configuration.handler;

import com.example.ezjob.common.ExceptionMessage;
import com.example.ezjob.exception.NotFoundException;
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApplicationExceptionHandler {

  private final ExceptionMessage exceptionMessage;

  @ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<ErrorMessage> handleUserAlreadyExistException(
          @Nonnull final HttpServletRequest request,
          @Nonnull final Exception exception) {
    log.error("Exception was thrown cause user already exists:", exception);
    final var message = ErrorMessage.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .date(new Date())
            .description(exceptionMessage.getUserAlreadyExistException())
            .url(request.getRequestURL().toString())
            .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

  @ExceptionHandler({AuthenticationException.class, JwtException.class})
  public ResponseEntity<ErrorMessage> handleAuthenticationException(
          @Nonnull final HttpServletRequest request,
          @Nonnull final Exception exception) {
    log.error("Exception was thrown during authentication:", exception);
    final var message = ErrorMessage.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .date(new Date())
            .description(exceptionMessage.getAuthenticationException())
            .url(request.getRequestURL().toString())
            .build();
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorMessage> handleNotFoundException(
          @Nonnull final HttpServletRequest request,
          @Nonnull final Exception exception) {
    log.error("Exception was thrown because resource was not found:", exception);
    final var message = ErrorMessage.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .date(new Date())
            .description(exceptionMessage.getNotFoundException())
            .url(request.getRequestURL().toString())
            .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> handleOtherExceptions(
          @Nonnull final HttpServletRequest request,
          @Nonnull final Exception exception) {
    log.error("Exception was thrown because resource was not found:", exception);
    final var message = ErrorMessage.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .date(new Date())
            .description(exceptionMessage.getNotFoundException())
            .url(request.getRequestURL().toString())
            .build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
  }
}
