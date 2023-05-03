package com.example.ezjob.service;

import com.example.ezjob.common.TestConstants.ResumeAttributes;
import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.repository.ResumeRepository;
import com.example.ezjob.service.impl.ResumeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResumeServiceImplTest {
  private final ResumeRequestDto RESUME_REQUEST = ResumeRequestDto.builder()
          .email(ResumeAttributes.EMAIL)
          .firstname(ResumeAttributes.FIRSTNAME)
          .lastname(ResumeAttributes.LASTNAME)
          .patronymic(ResumeAttributes.PATRONYMIC)
          .city(ResumeAttributes.CITY)
          .country(ResumeAttributes.COUNTRY)
          .phone(ResumeAttributes.PHONE)
          .university(ResumeAttributes.UNIVERSITY)
          .additionalInfo(ResumeAttributes.ADDITIONAL_INFO)
          .experience(ResumeAttributes.EXPERIENCE)
          .skillsList(ResumeAttributes.SKILLS_LIST)
          .position(ResumeAttributes.POSITION)
          .build();

  private final Resume RESUME = Resume.builder()
          .id(1L)
          .email(ResumeAttributes.EMAIL)
          .firstname(ResumeAttributes.FIRSTNAME)
          .lastname(ResumeAttributes.LASTNAME)
          .patronymic(ResumeAttributes.PATRONYMIC)
          .city(ResumeAttributes.CITY)
          .country(ResumeAttributes.COUNTRY)
          .phone(ResumeAttributes.PHONE)
          .university(ResumeAttributes.UNIVERSITY)
          .additionalInfo(ResumeAttributes.ADDITIONAL_INFO)
          .experience(ResumeAttributes.EXPERIENCE)
          .skillsList(ResumeAttributes.SKILLS_LIST)
          .position(ResumeAttributes.POSITION)
          .build();

  private final ResumeResponseDto RESUME_RESPONSE = ResumeResponseDto.builder()
          .email(ResumeAttributes.EMAIL)
          .firstname(ResumeAttributes.FIRSTNAME)
          .lastname(ResumeAttributes.LASTNAME)
          .patronymic(ResumeAttributes.PATRONYMIC)
          .city(ResumeAttributes.CITY)
          .country(ResumeAttributes.COUNTRY)
          .phone(ResumeAttributes.PHONE)
          .university(ResumeAttributes.UNIVERSITY)
          .additionalInfo(ResumeAttributes.ADDITIONAL_INFO)
          .experience(ResumeAttributes.EXPERIENCE)
          .skillsList(ResumeAttributes.SKILLS_LIST)
          .position(ResumeAttributes.POSITION)
          .build();

  @Mock
  ResumeRepository repository;
  @Mock
  ResumeMapper resumeMapper;
  @InjectMocks
  ResumeServiceImpl resumeService;
  @Test
  void shouldReturnValidResponseDtoWhenInvokedSaveResumeMethodWithValidResumeRequestDto() {
    when(resumeMapper.toResume(RESUME_REQUEST)).thenReturn(RESUME);
    when(repository.save(RESUME)).thenReturn(RESUME);
    when(resumeMapper.toResumeResponseDto(RESUME)).thenReturn(RESUME_RESPONSE);

    final var actualResumeResponse = resumeService.saveResume(RESUME_REQUEST);

    assertEquals(RESUME_RESPONSE, actualResumeResponse);
    verify(repository, times(1)).save(any());
  }

  @Test
  void shouldReturnValidResponseDtoWhenInvokedGetResumeByIdWithValidResumeRequestDto() {
    when(repository.findResumeById(RESUME.getId())).thenReturn(RESUME);
    when(resumeMapper.toResumeResponseDto(RESUME)).thenReturn(RESUME_RESPONSE);

    final var actualResumeResponse = resumeService.getResumeById(1L);

    assertEquals(RESUME_RESPONSE, actualResumeResponse);
    verify(repository, times(1)).findResumeById(any());
  }

  @Test
  void shouldReturnValidResponseDtoWhenInvokedUpdateResumeWithValidData() {
    when(resumeMapper.toResume(RESUME_REQUEST)).thenReturn(RESUME);
    when(resumeMapper.toResumeResponseDto(RESUME)).thenReturn(RESUME_RESPONSE);
    doNothing().when(resumeMapper).updateResume(RESUME, RESUME);
    when(repository.findResumeById(RESUME.getId())).thenReturn(RESUME);
    when(repository.save(RESUME)).thenReturn(RESUME);

    resumeService.updateResume(RESUME.getId(), RESUME_REQUEST);

    verify(repository, times(1)).findResumeById(RESUME.getId());
  }

  @Test
  void shouldReturnNothingWhenInvokedDeleteResumeWithProvidedId() {
    doNothing().when(repository).deleteById(RESUME.getId());

    resumeService.deleteResumeById(RESUME.getId());

    verify(repository, only()).deleteById(RESUME.getId());
  }
}
