package com.example.ezjob.service.impl;

import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.exception.ResumeNotFoundException;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.repository.jpa.ResumeRepository;
import com.example.ezjob.service.ResumeService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

import static java.lang.String.format;

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
    return repository.findById(id).orElseThrow(() -> new ResumeNotFoundException(
            format("Resume with id = %d not found.", id)));
  }

  @Override
  @Transactional
  public Resume updateResume(@Nonnull Long id, @Nonnull @Valid ResumeRequestDto resumeDto) {
    final var resume = resumeMapper.toResume(resumeDto);
    resume.setId(id);

    final var resumeToUpdate = repository.findById(id).orElseThrow(() -> new ResumeNotFoundException(
            format("Resume with id = %d not found.", id)));

    resumeMapper.updateResume(resumeToUpdate, resume);

    return repository.save(resumeToUpdate);
  }

  @Override
  @Transactional
  public void deleteResumeById(@Nonnull Long id) {
    repository.deleteById(id);
  }
}
