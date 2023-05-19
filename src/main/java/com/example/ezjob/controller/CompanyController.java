package com.example.ezjob.controller;

import com.example.ezjob.common.mapper.CompanyMapper;
import com.example.ezjob.configuration.security.jwt.JwtTokenUtil;
import com.example.ezjob.model.dto.CompanyRequestDto;
import com.example.ezjob.model.dto.CompanyResponseDto;
import com.example.ezjob.service.AuthenticationUserService;
import com.example.ezjob.service.CompanyService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RequestMapping("/company")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
  private final CompanyService companyService;
  private final CompanyMapper companyMapper;
  private final JwtTokenUtil jwtTokenUtil;
  private final AuthenticationUserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CompanyResponseDto createCompany(final HttpServletRequest request,
                                          @RequestBody @Nonnull @Valid final CompanyRequestDto companyRequest) {
    final var token = jwtTokenUtil.parseJwt(request);
    final var username = jwtTokenUtil.getUsername(token);
    final var authUser = userService.getUserByUsername(username);

    final var company = companyMapper.toCompany(companyRequest);
    company.setAuthUser(authUser);

    final var response = companyService.saveCompany(company);
    return companyMapper.toCompanyResponseDto(response);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CompanyResponseDto getCompanyById(@PathVariable @Nonnull @Min(1) final Long id) {
    final var response = companyService.getCompanyById(id);
    return companyMapper.toCompanyResponseDto(response);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CompanyResponseDto updateCompany(@PathVariable @Nonnull @Min(1) final Long id,
                                          @RequestBody @Nonnull @Valid final CompanyRequestDto companyRequest) {
    final var response = companyService.updateCompany(id, companyRequest);
    return companyMapper.toCompanyResponseDto(response);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteCompany(@PathVariable @Nonnull @Min(1) final Long id) {
    companyService.deleteCompany(id);
  }
}
