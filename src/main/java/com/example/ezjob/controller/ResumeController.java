package com.example.ezjob.controller;

import com.example.ezjob.common.mapper.ResumeMapper;
import com.example.ezjob.configuration.security.jwt.JwtTokenUtil;
import com.example.ezjob.model.dto.ResumeRequestDto;
import com.example.ezjob.model.dto.ResumeResponseDto;
import com.example.ezjob.service.AuthenticationUserService;
import com.example.ezjob.service.ResumeService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RequestMapping("/resume")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ResumeController {
  private final ResumeService resumeService;
  private final ResumeMapper resumeMapper;
  private final JwtTokenUtil jwtTokenUtil;
  private final AuthenticationUserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResumeResponseDto createResume(final HttpServletRequest request,
                                        @RequestBody @Nonnull @Valid final ResumeRequestDto resumeRequest) {
    final var token = jwtTokenUtil.parseJwt(request);
    final var username = jwtTokenUtil.getUsername(token);
    final var authUser = userService.getUserByUsername(username);

    final var resume = resumeMapper.toResume(resumeRequest);
    resume.setAuthUser(authUser);

    final var response = resumeService.saveResume(resume);
    return resumeMapper.toResumeResponseDto(response);
  }

  @PostMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResumeResponseDto responseToVacancy(@PathVariable @Nonnull @Min(1) final Long id,
                                             @RequestParam @Nonnull @Valid final Long vacancyId) {
    final var response = resumeService.addVacancy(id, vacancyId);
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
