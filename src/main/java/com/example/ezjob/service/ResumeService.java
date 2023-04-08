package com.example.ezjob.service;

import com.example.ezjob.persistense.entity.Resume;
import javax.validation.constraints.NotNull;

public interface ResumeService {
  Resume saveResume(@NotNull Resume resume);
  Resume getResumeById(@NotNull Long id);
}
