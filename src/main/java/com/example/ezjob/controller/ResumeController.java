package com.example.ezjob.controller;

import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/resume")
public class ResumeController {
  private ResumeService resumeService;
  private ResumeMapper resumeMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResumeResponseDto create(ResumeRequestDto resumeRequest) {
    final var resume = resumeMapper.toResume(resumeRequest);
    final var createdResume = resumeService.saveResume(resume);
    return resumeMapper.toResumeResponseDto(createdResume);
  }
}
