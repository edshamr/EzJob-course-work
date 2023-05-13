package com.example.ezjob.controller;

import com.example.ezjob.common.mapper.ProfileMapper;
import com.example.ezjob.configuration.security.jwt.JwtTokenUtil;
import com.example.ezjob.model.dto.ProfileResponseDto;
import com.example.ezjob.persistense.entity.Resume;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileMapper profileMapper;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponseDto getProfile(HttpServletRequest request,
                                         @Nonnull @RequestParam Resume resume) {
        final var token = jwtTokenUtil.parseJwt(request);
        final var username = jwtTokenUtil.getUsername(token);
        final var response = profileMapper.toProfileResponseDto(username, resume);
        return response;
    }
}
