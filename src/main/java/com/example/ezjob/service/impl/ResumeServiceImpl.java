package com.example.ezjob.service.impl;

import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.repository.ResumeRepository;
import com.example.ezjob.service.ResumeService;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResumeServiceImpl implements ResumeService {
  private ResumeRepository repository;

  @Override
  @Transactional
  public Resume saveResume(Resume resume) {
    return repository.save(resume);
  }

  @Override
  public Resume getResumeById(Long id) {
    return repository.findResumeById(id);
  }
}
