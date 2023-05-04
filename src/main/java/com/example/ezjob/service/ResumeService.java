package com.example.ezjob.service;

import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.persistense.entity.Resume;
import jakarta.annotation.Nonnull;

public interface ResumeService {
  Resume saveResume(@Nonnull ResumeRequestDto resume);
  Resume getResumeById(@Nonnull Long id);
  Resume updateResume(@Nonnull Long id, @Nonnull ResumeRequestDto resume);
  void deleteResumeById(@Nonnull Long id);
}
