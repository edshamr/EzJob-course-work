package com.example.ezjob.common.mapper;

import com.example.ezjob.common.TestConstants.ResumeAttributes;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.persistense.entity.Resume;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResumeMapperTest {

  private static final ResumeRequestDto RESUME_REQUEST = ResumeRequestDto.builder()
          .email(ResumeAttributes.EMAIL)
          .firstname(ResumeAttributes.FIRSTNAME)
          .lastname(ResumeAttributes.LASTNAME)
          .patronymic(ResumeAttributes.PATRONYMIC)
          .city(ResumeAttributes.CITY)
          .country(ResumeAttributes.COUNTRY)
          .phone(ResumeAttributes.PHONE)
          .position(ResumeAttributes.POSITION)
          .university(ResumeAttributes.UNIVERSITY)
          .skillsList(ResumeAttributes.SKILLS_LIST)
          .experience(ResumeAttributes.EXPERIENCE)
          .additionalInfo(ResumeAttributes.ADDITIONAL_INFO)
          .build();

  private static final Resume RESUME = Resume.builder()
          .email(ResumeAttributes.EMAIL)
          .firstname(ResumeAttributes.FIRSTNAME)
          .lastname(ResumeAttributes.LASTNAME)
          .patronymic(ResumeAttributes.PATRONYMIC)
          .city(ResumeAttributes.CITY)
          .country(ResumeAttributes.COUNTRY)
          .phone(ResumeAttributes.PHONE)
          .position(ResumeAttributes.POSITION)
          .university(ResumeAttributes.UNIVERSITY)
          .skillsList(ResumeAttributes.SKILLS_LIST)
          .experience(ResumeAttributes.EXPERIENCE)
          .additionalInfo(ResumeAttributes.ADDITIONAL_INFO)
          .build();

  private ResumeMapper resumeMapper = new ResumeMapperImpl();

  @Test
  void toResumeShouldReturnResumeBasedOnDto() {
    final var resume = resumeMapper.toResume(RESUME_REQUEST);

    assertEquals(ResumeAttributes.EMAIL, resume.getEmail());
    assertEquals(ResumeAttributes.FIRSTNAME, resume.getFirstname());
    assertEquals(ResumeAttributes.LASTNAME, resume.getLastname());
    assertEquals(ResumeAttributes.PATRONYMIC, resume.getPatronymic());
    assertEquals(ResumeAttributes.CITY, resume.getCity());
    assertEquals(ResumeAttributes.COUNTRY, resume.getCountry());
    assertEquals(ResumeAttributes.PHONE, resume.getPhone());
    assertEquals(ResumeAttributes.POSITION, resume.getPosition());
    assertEquals(ResumeAttributes.UNIVERSITY, resume.getUniversity());
    assertEquals(ResumeAttributes.SKILLS_LIST, resume.getSkillsList());
    assertEquals(ResumeAttributes.EXPERIENCE, resume.getExperience());
    assertEquals(ResumeAttributes.ADDITIONAL_INFO, resume.getAdditionalInfo());
  }

  @Test
  void toResumeResponseDtoShouldReturnDtoBasedOnResume() {
    final var resumeDto = resumeMapper.toResumeResponseDto(RESUME);

    assertEquals(ResumeAttributes.EMAIL, resumeDto.getEmail());
    assertEquals(ResumeAttributes.FIRSTNAME, resumeDto.getFirstname());
    assertEquals(ResumeAttributes.LASTNAME, resumeDto.getLastname());
    assertEquals(ResumeAttributes.PATRONYMIC, resumeDto.getPatronymic());
    assertEquals(ResumeAttributes.CITY, resumeDto.getCity());
    assertEquals(ResumeAttributes.COUNTRY, resumeDto.getCountry());
    assertEquals(ResumeAttributes.PHONE, resumeDto.getPhone());
    assertEquals(ResumeAttributes.POSITION, resumeDto.getPosition());
    assertEquals(ResumeAttributes.UNIVERSITY, resumeDto.getUniversity());
    assertEquals(ResumeAttributes.SKILLS_LIST, resumeDto.getSkillsList());
    assertEquals(ResumeAttributes.EXPERIENCE, resumeDto.getExperience());
    assertEquals(ResumeAttributes.ADDITIONAL_INFO, resumeDto.getAdditionalInfo());
  }
}
