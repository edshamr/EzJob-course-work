package com.example.ezjob.controller;


import com.example.ezjob.common.mapper.ProfileMapper;
import com.example.ezjob.configuration.security.jwt.JwtTokenUtil;
import com.example.ezjob.model.dto.CompanyProfileResponseDto;
import com.example.ezjob.persistense.entity.Company;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company/profile")
@RequiredArgsConstructor
public class CompanyProfileController {
    private final ProfileMapper profileMapper;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CompanyProfileResponseDto getCompanyProfile(HttpServletRequest request,
                                                       @Nonnull @RequestParam Company company) {
        final var token = jwtTokenUtil.parseJwt(request);
        final var username = jwtTokenUtil.getUsername(token);
        final var response = profileMapper.toProfileResponseDto(username, company);
        return response;
    }
}
