package com.example.ezjob.common;

import java.util.Calendar;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstants {
  @UtilityClass
  public class FieldsConstraints {
    public static final int MAX_LOGIN_SIZE = 30;
    public static final int MIN_LOGIN_SIZE = 5;
    public static final int MAX_PASSWORD_SIZE = 30;
    public static final int MIN_PASSWORD_SIZE = 5;
  }

  @UtilityClass
  public class Web {
    @UtilityClass
    public class Security {
      public static final String SERVER_TIMEZONE_ID =
              Calendar.getInstance().getTimeZone().toZoneId().toString();
      public static final String TOKEN_PREFIX = "Bearer ";
      public static final String BLACKLIST_CACHE_NAME = "jwt-black-list";
      public static final String TOKEN_HEADER_NAME = "Authorization";
    }
  }

  /**
   * Inner utility class for constants related to security jwt claims part.
   */
  @UtilityClass
  public class JwtClaims {
    public static final String ROLE = "role";
    public static final String TIME_ZONE_ID = "timeZoneId";
  }
}
