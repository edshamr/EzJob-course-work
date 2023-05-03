package com.example.ezjob.service.impl;

import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.persistense.entity.Resume;
import com.example.ezjob.persistense.repository.ResumeRepository;
import com.example.ezjob.service.ResumeService;
import jakarta.annotation.Nonnull;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResumeServiceImpl implements ResumeService {
  private ResumeRepository repository;
  private ResumeMapper resumeMapper;

  @Override
  @Transactional
  public ResumeResponseDto saveResume(@Nonnull @Valid ResumeRequestDto resumeDto) {
    final var resume = resumeMapper.toResume(resumeDto);
    Resume savedResume = repository.save(resume);
    return resumeMapper.toResumeResponseDto(savedResume);
  }

  @Override
  public ResumeResponseDto getResumeById(@Nonnull Long id) {
    Resume resume = repository.findResumeById(id);
    return resumeMapper.toResumeResponseDto(resume);
  }

  @Override
  public ResumeResponseDto updateResume(@Nonnull Long id, @Nonnull @Valid ResumeRequestDto resumeDto) {
    final var resume = resumeMapper.toResume(resumeDto);
    final var resumeToUpdate = repository.findResumeById(id);

    resumeMapper.updateResume(resumeToUpdate, resume);

    Resume savedResume = repository.save(resumeToUpdate);
    return resumeMapper.toResumeResponseDto(savedResume);
  }

  @Override
  @Transactional
  public void deleteResumeById(@Nonnull Long id) {
    repository.deleteById(id);
  }
}
