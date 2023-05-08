package com.example.ezjob.common;

import com.example.ezjob.persistense.entity.RoleName;
import java.util.Set;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class TestConstants {

  @UtilityClass
  public class Path {
    public String LOGIN_PATH = "/auth/login";
    public String REGISTRATION_PATH = "/auth/register";
  }
  @UtilityClass
  public class UserAttributes {
    public Long ID = 33L;
    public String USERNAME = "testUsername";
    public String PASSWORD = "$2a$12$HFmth1tRCCVAzCjMu7rTJOB2UjbdcNJkkOahFdJhTAvzbiINUdt8C";
    public String EMAIL = "testUser@gmail.com";
    public Set<RoleName> ROLES = Set.of(RoleName.USER);
  }
  @UtilityClass
  public class ResumeAttributes {
    public String EMAIL = "test@gmail.com";

    public String FIRSTNAME = "testName";

    public String LASTNAME = "testLastname";

    public String PATRONYMIC = "testPatronymic";

    public String CITY = "Lviv";

    public String COUNTRY = "Ukraine";

    public String PHONE = "555555555";

    public String POSITION  = "Junior";

    public String UNIVERSITY = "TestUniversity";

    public List<String> SKILLS_LIST = List.of("Testing", "Spring");

    public int EXPERIENCE = 1;

    public String ADDITIONAL_INFO = "End some courses";
  }
  @UtilityClass
  public class JwtTokenAttributes {
    public final String TOKEN_PREFIX = "Bearer ";
    public String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInRpbWVab25lSWQiOiJBc2lhL0JlaXJ1dCIsInN1YiI6InRlc3RVc2VybmFtZSIsImlhdCI6MTY4MzMxNjIwMiwiZXhwIjoxNjgzMzUyMjAyfQ.Uen6diE3qSsKXFu-cwHP94FN_oV1GHFcf5h0Fz_235I";
    public String HEADER = "Authorization";
  }

  @UtilityClass
  public class InvalidData {
    @UtilityClass
    public class InvalidJwtTokenAttributes {
      public String INVALID_TOKEN = "invalid token";
    }
  }
}
