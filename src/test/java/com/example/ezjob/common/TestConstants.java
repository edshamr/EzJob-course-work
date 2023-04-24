package com.example.ezjob.common;

import com.example.ezjob.persistense.entity.RoleName;
import java.util.Set;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class TestConstants {
  @UtilityClass
  public class UserAttributes {
    public Long ID = 33L;
    public String USERNAME = "testUsername";
    public String PASSWORD = "$2a$12$HFmth1tRCCVAzCjMu7rTJOB2UjbdcNJkkOahFdJhTAvzbiINUdt8C";
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
    public String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInRpbWVab25lSW" +
            "QiOiJBc2lhL0JlaXJ1dCIsInN1YiI6InRlc3RVc2VybmFtZSIsImlhdCI6MTY4MjI5MDg3NiwiZXhwIjoxNjgyMzI2ODc2fQ.FGKrsLrGSL0WR26Uqg2HjVAahUb3OE5oOZM7R0FBZ1k";
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
