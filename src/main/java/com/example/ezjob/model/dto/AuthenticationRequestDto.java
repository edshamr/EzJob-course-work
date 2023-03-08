package com.example.ezjob.model.dto;

import com.example.ezjob.common.ApplicationConstants;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * DTO class for authentication (login) request.
 */
@EqualsAndHashCode
@ToString
@Getter
@Builder
public class AuthenticationRequestDto {
  @Size(min = ApplicationConstants.FieldsConstraints.MIN_LOGIN_SIZE,
          max = ApplicationConstants.FieldsConstraints.MAX_LOGIN_SIZE)
  @NotEmpty
  @NotBlank
  private String username;

  @Size(min = ApplicationConstants.FieldsConstraints.MIN_PASSWORD_SIZE,
          max = ApplicationConstants.FieldsConstraints.MAX_PASSWORD_SIZE)
  @NotEmpty
  @NotBlank
  private String password;
}
