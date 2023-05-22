package com.example.ezjob.service;

import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.persistense.entity.Resume;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import javax.validation.Valid;
import java.util.List;

public interface ResumeService {
  Resume addVacancy(@Nonnull final Long id, @Nonnull final Long vacancyId);

  List<Resume> getResumes(@Nullable String position,
                          @Nullable String country,
                          int experience);

  Resume saveResume(@Nonnull @Valid Resume resume);

  Resume getResumeById(@Nonnull Long id);

  Resume updateResume(@Nonnull Long id, @Nonnull ResumeRequestDto resume);

  void deleteResumeById(@Nonnull Long id);
}
