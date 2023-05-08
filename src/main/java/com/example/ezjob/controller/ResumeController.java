package com.example.ezjob.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.service.ResumeService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/resume")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ResumeController {
  private final ResumeService resumeService;
  private final ResumeMapper resumeMapper;
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
