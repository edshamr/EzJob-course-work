package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.repository.ResumeRepository;
import com.example.ezjob.service.impl.ResumeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResumeServiceImplTest {
  @Mock
  ResumeRepository repository;
  @InjectMocks
  ResumeServiceImpl resumeService;
  @Test
  void saveResumeShouldSaveResumeOnceAndReturnCreatedResume() {
    final var resume = Resume.builder().build();
    when(repository.save(resume)).thenReturn(resume);

    final var actualResume = resumeService.saveResume(resume);

    assertEquals(resume, actualResume);
    verify(repository, times(1)).save(any());
  }

  @Test
  void getResumeByIdShouldReturnResumeWithMatchingId() {
    final var resume = Resume.builder()
            .id(1L)
            .build();
    when(repository.findResumeById(resume.getId())).thenReturn(resume);

    final var actualResume = resumeService.getResumeById(1L);

    assertEquals(resume, actualResume);
    verify(repository, times(1)).findResumeById(any());
  }
}
