package com.example.ezjob.service;

import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.persistense.entity.Resume;
import jakarta.annotation.Nonnull;

public interface ResumeService {
  ResumeResponseDto saveResume(@Nonnull ResumeRequestDto resume);
  ResumeResponseDto getResumeById(@Nonnull Long id);
  ResumeResponseDto updateResume(@Nonnull Long id, @Nonnull ResumeRequestDto resume);
  void deleteResumeById(@Nonnull Long id);
}
