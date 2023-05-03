package com.example.ezjob.controller;

import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.service.ResumeService;
import jakarta.annotation.Nonnull;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResumeResponseDto createResume(ResumeRequestDto resumeRequest) {
    return resumeService.saveResume(resumeRequest);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResumeResponseDto getResumeById(@PathVariable @Nonnull @Min(1) final Long id) {
    return resumeService.getResumeById(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResumeResponseDto updateResume(@PathVariable @Nonnull @Min(1) final Long id,
                                        @RequestBody @Nonnull @Valid final ResumeRequestDto resumeRequest) {
    return resumeService.updateResume(id, resumeRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteResume(@PathVariable @Nonnull @Min(1) final Long id) {
    resumeService.deleteResumeById(id);
  }
}
