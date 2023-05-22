package com.example.ezjob.common;

import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

            public static final Map<String, String> ALLOWED_PATH = Stream.of(new Object[][]{
                    {"/api/company/**", "COMPANY"},
                    {"/api/user/**", "USER"}
            }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
        }
    }

    /**
     * Inner utility class for constants related to security jwt claims part.
     */
    @UtilityClass
    public class JwtClaims {
        public static final String ROLE = "role";
        public static final String EMAIL = "email";
        public static final String TIME_ZONE_ID = "timeZoneId";
    }

    @UtilityClass
    public class EmailConfirmReplyAttributes {
        @UtilityClass
        public class UserEmailAttributes {
            public static final String SUBJECT = "Response to Vacancy";
            public static final String MESSAGE_BODY = "Your response to the vacancy has been submitted.";
        }
        @UtilityClass
        public class CompanyEmailAttributes {
            public static final String SUBJECT = "Response on the vacancy.";
            public static final String MESSAGE_BODY = "You get new reply on the vacancy.";
        }

    }
}
