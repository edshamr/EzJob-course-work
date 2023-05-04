package com.example.ezjob.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.service.ResumeService;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/resume")
public class ResumeController {
  private ResumeService resumeService;
  private ResumeMapper resumeMapper;
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResumeResponseDto createResume(ResumeRequestDto resumeRequest) {
    final var response = resumeService.saveResume(resumeRequest);
    return resumeMapper.toResumeResponseDto(response);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResumeResponseDto getResumeById(@PathVariable @Nonnull @Min(1) final Long id) {
    final var response = resumeService.getResumeById(id);
    return resumeMapper.toResumeResponseDto(response);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResumeResponseDto updateResume(@PathVariable @Nonnull @Min(1) final Long id,
                                        @RequestBody @Nonnull @Valid final ResumeRequestDto resumeRequest) {
    final var response = resumeService.updateResume(id, resumeRequest);
    return resumeMapper.toResumeResponseDto(response);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteResume(@PathVariable @Nonnull @Min(1) final Long id) {
    resumeService.deleteResumeById(id);
  }
}
