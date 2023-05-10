package com.example.ezjob.service.impl;

import javax.validation.Valid;
import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.repository.jpa.ResumeRepository;
import com.example.ezjob.service.ResumeService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
  private final ResumeRepository repository;
  private final ResumeMapper resumeMapper;

  @Override
  @Transactional
  public Resume saveResume(@Nonnull @Valid ResumeRequestDto resumeDto) {
    final var resume = resumeMapper.toResume(resumeDto);
    return repository.save(resume);
  }

  @Override
  public Resume getResumeById(@Nonnull Long id) {
    return repository.findResumeById(id);
  }

  @Override
  @Transactional
  public Resume updateResume(@Nonnull Long id, @Nonnull @Valid ResumeRequestDto resumeDto) {
    final var resume = resumeMapper.toResume(resumeDto);
    final var resumeToUpdate = repository.findResumeById(id);

    resumeMapper.updateResume(resumeToUpdate, resume);

    return repository.save(resumeToUpdate);
  }

  @Override
  @Transactional
  public void deleteResumeById(@Nonnull Long id) {
    repository.deleteById(id);
  }
}
