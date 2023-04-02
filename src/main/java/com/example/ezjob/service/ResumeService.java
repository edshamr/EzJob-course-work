package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.Resume;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

public interface ResumeService {
  Resume saveResume(@NotNull final Resume resume);
  Resume getResumeById(@NotNull final Long id);
}
