package com.example.ezjob.service.impl;

import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.repository.ResumeRepository;
import com.example.ezjob.service.ResumeService;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {
  private ResumeRepository repository;

  @Override
  public Resume saveResume(Resume resume) {
    return repository.save(resume);
  }

  @Override
  public Resume getResumeById(Long id) {
    return repository.findResumeById(id);
  }
}
