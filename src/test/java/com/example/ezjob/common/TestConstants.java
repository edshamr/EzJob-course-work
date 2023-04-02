package com.example.ezjob.common;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class TestConstants {
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
}
